package de.gamingcraft.utils;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class Root {
    public String url;
    public String assets_url;
    public String upload_url;
    public String html_url;
    public int id;
    public String node_id;
    public String tag_name;
    public String target_commitish;
    public String name;
    public boolean draft;
    public Author author;
    public boolean prerelease;
    public Date created_at;
    public Date published_at;
    public List<Asset> assets;
    public String tarball_url;
    public String zipball_url;
    @Expose public String body;
}

class Author{
    public String login;
    public int id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
}

class Uploader{
    public String login;
    public int id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
}

class Asset{
    public String url;
    public int id;
    public String node_id;
    public String name;
    public Object label;
    public Uploader uploader;
    public String content_type;
    public String state;
    public int size;
    public int download_count;
    public Date created_at;
    public Date updated_at;
    public String browser_download_url;
}
