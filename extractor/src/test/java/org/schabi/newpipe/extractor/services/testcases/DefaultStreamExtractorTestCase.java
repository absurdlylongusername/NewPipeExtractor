package org.schabi.newpipe.extractor.services.testcases;

import static org.schabi.newpipe.extractor.stream.StreamExtractor.UNKNOWN_SUBSCRIBER_COUNT;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import javax.annotation.Nullable;

/**
 * Test case base class for {@link org.schabi.newpipe.extractor.services.DefaultStreamExtractorTest}<p>
 * Ideally you will supply a regex matcher that the url that will automatically parse
 * certain values for the tests.<p>
 * Ones that can't be derived from the url should be overridden in the test case.
 */
public interface DefaultStreamExtractorTestCase extends DefaultExtractorTestCase {
    /**
     * Returns matcher for the URL<p>
     * Implementations should throw IllegalArgumentException if the pattern does not match
     */
    public abstract Matcher urlMatcher();

    public default String getGroupFromUrl(String groupName) {
        return urlMatcher().group(groupName);
    }

    public default int getGroupEndIndexFromUrl(String groupName) {
        return urlMatcher().end(groupName);
    }

    public default String id() { return getGroupFromUrl("id"); }

    public default String uploader() { return getGroupFromUrl("uploader"); }
    
    public abstract StreamType streamType();
    public abstract String uploaderName();
    public default String uploaderUrl() {
        final int groupEndIndex = getGroupEndIndexFromUrl("uploader");
        if (groupEndIndex < 0) {
            return ""; // no uploader group found in url
        }
        return url().substring(0, groupEndIndex);
    }
    public default boolean uploaderVerified() { return false; }
    public default long uploaderSubscriberCountAtLeast() { return UNKNOWN_SUBSCRIBER_COUNT; } // default: unknown
    public default String subChannelName() { return ""; } // default: no subchannel
    public default String subChannelUrl() { return ""; } // default: no subchannel
    public default boolean descriptionIsEmpty() { return false; } // default: description is not empty
    public abstract List<String> descriptionContains();
    public abstract long length();
    public default int timestamp() { return 0; } // default: there is no timestamp
    public abstract long viewCountAtLeast();

    /**
     *  format: yyyy-MM-dd HH:mm:ss.SSS
     */
    @Nullable public abstract String uploadDate();
    @Nullable public abstract String textualUploadDate();
    public abstract long likeCountAtLeast();
    public abstract long dislikeCountAtLeast();
    public default boolean hasRelatedItems() { return true; } // default: there are related videos
    public default int ageLimit() { return StreamExtractor.NO_AGE_LIMIT; } // default: no limit
    @Nullable public default String errorMessage() { return null; } // default: no error message
    public default boolean hasVideoStreams() { return true; } // default: there are video streams
    public default boolean hasAudioStreams() { return true; } // default: there are audio streams
    public default boolean hasSubtitles() { return true; } // default: there are subtitles streams
    @Nullable public default String dashMpdUrlContains() { return null; } // default: no dash mpd
    public default boolean hasFrames() { return true; } // default: there are frames
    @Nullable public default String host() { return ""; } // default: no host for centralized platforms
    @Nullable public default StreamExtractor.Privacy privacy() { return StreamExtractor.Privacy.PUBLIC; } // default: public
    public default String category() { return ""; } // default: no category
    public default String licence() { return ""; } // default: no licence
    @Nullable public default Locale languageInfo() { return null; } // default: no language info available    
    @Nullable public default List<String> tags() { return Collections.emptyList(); } // default: no tags
    @Nullable public default String supportInfo() { return ""; } // default: no support info available
    public default int streamSegmentsCount() { return -1; } // return 0 or greater to test (default is -1 to ignore)
    @Nullable public default List<MetaInfo> metaInfo() { return Collections.emptyList(); } // default: no metadata info available
}
