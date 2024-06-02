package inga.springpropertyloader;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public record Input(
        Path from,
        List<String> profiles,
        @Deprecated
        List<String> profileCandidates
) {
    public Input {
        profiles = profiles == null ? Collections.emptyList() : profiles;
        if (profiles.isEmpty()) {
            profiles = profileCandidates == null ? Collections.emptyList() : profileCandidates;
        }
    }
}
