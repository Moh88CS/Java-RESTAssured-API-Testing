package arguments.holders;

import java.util.Map;

public record CardBodyValidationArgumentsHolder(Map<String, Object> bodyParams, String errorMessage) {
}
