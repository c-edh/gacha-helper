package edu.csustan.cs3810.gacha_helper;

public class UserBuild {

    private String ArtifactName;
    private int ArtifactLevel;

    private String ArtifactMainStat;

    private String ArtifactSubStat;

    public UserBuild(){}

    public UserBuild(String artifact, int level, String mainstat, String substat) {
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
