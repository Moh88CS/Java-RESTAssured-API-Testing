package consts;

import java.util.Map;

public class UrlParamValues {

    public static final String EXISTING_BOARD_ID = "670bfaf62656fd3e8f5e7549";
    public static final String EXISTING_CARD_ID = "670c4ddf8eeaca6bf176696e";
    public static final String EXISTING_LIST_ID = "670bfb7956de20d533863709";
    public static final String BOARD_ID_TO_UPDATE = "670c37e377a68c3d1131d0d9";
    public static final String CARD_ID_TO_UPDATE = "670c3af3b378307bd2db94b4";
    public static final String USER_NAME = "mohamedabdulal";
    public static final String VALID_KEY = "e9ec7223857773c53964cc7286ee6da5";
    public static final String VALID_TOKEN = "ATTAbdb870c70e23e66ac72f51077bd8512b477cc9a72a8043456b4b86f3d64c2897D9A2F1F3";

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "e9ec7223857773c53964cc7286ee6da5",
            "token", "ATTAbdb870c70e23e66ac72f51077bd8512b477cc9a72a8043456b4b86f3d64c2897D9A2F1F0"
    );
}
