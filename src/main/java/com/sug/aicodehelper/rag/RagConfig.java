package com.sug.aicodehelper.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.FileSystem;
import java.util.List;

@Configuration
public class RagConfig
{
    @Resource
    EmbeddingModel qwenEmbeddingModel;
//    @Resource
//    EmbeddingStore<TextSegment> embeddingStore;
    EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();


    @Bean
    public ContentRetriever contentRetriever()
    {
        //1.加载文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docx");
        //2.文档切割,按照段落切割,最大1000个字符，最多重复200个字符
        DocumentByParagraphSplitter documentByParagraphSplitter =
                new DocumentByParagraphSplitter(1000, 200);
        //3.自定义文档加载器,把文档转成向量存到向量数据库
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                //提高文档质量在文档碎片的开头加上文档名字
                .textSegmentTransformer(segment -> TextSegment.from(   // 注意方法名
                        segment.metadata().getString("file_name") + "\n" + segment.text(),
                        segment.metadata()))
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        ingestor.ingest(documents);
        //4.自定义内容加载器
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(5)
                .minScore(0.75)
                .build();
        return contentRetriever;
    };
}
