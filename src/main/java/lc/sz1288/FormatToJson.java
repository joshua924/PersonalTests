package lc.sz1288;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class FormatToJson {
    public static void main(String[] args) {
        String line = "#glacier/igloo/UtilizationEntryRecord{:image-id \"imageV1.SrjtdkWNxCfPm64P7xJGssLs\", :image-size 1063004455, :deleted-size 0, :archive-count 50, :archive-ids-checksum \"c9cc229fc65ceed5216540d7af1fe222083d841e6761f78491137496e17a02bs\", :checksum-algorithm \"v1\", :image-type \"Classic\", :version 1}";
        String json = formatToJson(line);
        System.out.println(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        System.out.println(gson.toJson(jsonObject));
    }

    private static String formatToJson(String line) {
        line = line.replaceFirst(".*\\{", "{");
        line = line.replaceAll(":([a-z|-]*) ", "\"$1\": ");
        return line;
    }
}
