package inga.springpropertyloader;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public record Input(
        Path from,
        List<String> profileCandidates
) {
    public Input {
        profileCandidates = profileCandidates == null ? Collections.emptyList() : profileCandidates;
    }
}
