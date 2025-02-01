package arguments.holders;

import java.util.Map;

public record CardIdValidationArgumentsHolder(Map<String, String> pathParams, int statusCode, String errorMessage) {

}
