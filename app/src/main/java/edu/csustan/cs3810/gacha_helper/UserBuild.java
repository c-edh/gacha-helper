package edu.csustan.cs3810.gacha_helper;

public class UserBuild {

    private String ArtifactName;
    private int ArtifactLevel;

    private String ArtifactMainStat;

    private String ArtifactSubStat;

    public UserBuild(){}

    public UserBuild(String artifactName, int level, String artifactMainStat, String artifactSubStat) {
        ArtifactName = artifactName;
        ArtifactLevel = level;
        ArtifactMainStat = artifactMainStat;
        ArtifactSubStat = artifactSubStat;
    }

    public String getArtifactName(){
        return ArtifactName;
    }

    public int getArtifactLevel() {
        return ArtifactLevel;
    }

    public String getArtifactMainStat() {
        return ArtifactMainStat;
    }

    public String getArtifactSubStat() {
        return ArtifactSubStat;
    }

}
