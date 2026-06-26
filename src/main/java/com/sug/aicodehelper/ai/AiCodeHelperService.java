package com.sug.aicodehelper.ai;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;

import java.util.List;

public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);
    record Report(String name, List<String> suggestList){};
    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatWithReport(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRag(String userMessage);
}
