package com.github.emsp.simulator.model.emsp;

public class Version implements Comparable<Version> {
    private String version;
    private String url;

    public Version() {
    }

    public Version(String version, String url) {
        this.version = version;
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(Version other) {
        return other.getVersion().compareTo(version);
    }

}
