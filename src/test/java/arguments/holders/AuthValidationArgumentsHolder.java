package arguments.holders;

import java.util.Map;

public record AuthValidationArgumentsHolder(Map<String, String> authParams, String errorMessage) {

}
