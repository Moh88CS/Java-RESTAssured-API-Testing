package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class CardAuthValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "unauthorized card permission requested"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of(
                                "key", UrlParamValues.VALID_KEY
                        ),
                        "unauthorized card permission requested"
                ),
                new AuthValidationArgumentsHolder(
                       Map.of(
                                "token", "ATTAbdb870c70e23e66ac72f51077bd8512b477cc9a72a8043456b4b86f3d64c2897D9A2F1F3"
                        ),
                        "invalid app key"
                )
        ).map(Arguments::of);
    }
}
