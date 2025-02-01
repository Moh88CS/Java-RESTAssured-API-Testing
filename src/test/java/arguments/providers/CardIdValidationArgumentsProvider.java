package arguments.providers;

import arguments.holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "invalid id"),
                        400,
                        "invalid id"
                ),
                new CardIdValidationArgumentsHolder(
                        Map.of("id", "670c4ddf8eeaca6bf1766961"),
                        404,
                        "The requested resource was not found."
                )
        ).map(Arguments::of);
    }
}
