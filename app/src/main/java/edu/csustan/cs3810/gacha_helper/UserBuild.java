package edu.csustan.cs3810.gacha_helper;


//Corey Edh

import android.renderscript.Sampler;

import java.util.Map;

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
    public UserBuild(Map<String, ?> data) {
        ArtifactName = data.get("artifactName").toString();
        int level = 0;
        level = ((Number) data.get("artifactLevel")).intValue();
        ArtifactLevel = level;
        ArtifactMainStat = data.get("artifactMainStat").toString();
        ArtifactSubStat = data.get("artifactSubStat").toString();
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

    public void setArtifactName(String artifactName) {
        ArtifactName = artifactName;
    }

    public void setArtifactLevel(int artifactLevel) {
        ArtifactLevel = artifactLevel;
    }

    public void setArtifactMainStat(String artifactMainStat) {
        ArtifactMainStat = artifactMainStat;
    }

    public void setArtifactSubStat(String artifactSubStat) {
        ArtifactSubStat = artifactSubStat;
    }

}
