package smaug.cloud.common.media;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by naonao on 17/10/21.
 */
public class SmaugMediaType extends MediaType {
    public SmaugMediaType(String type, String subtype, Charset charset) {
        super(type, subtype, charset);
    }
}
