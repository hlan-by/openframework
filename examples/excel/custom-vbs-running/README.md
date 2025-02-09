# Running of custom VB script

This process example demonstrates how to run custom VB script for some spreadsheet document using Excel package functionality.

```java
    @Override
    public void execute() {
        String sourceSpreadsheetFile = "test.xlsx";
        String vbScriptFile = "test_script.vbs";
        String outputSpreadsheetFile = "output.xlsx";

        log.info("Run VB script '{}' for spreadsheet document located at '{}'", vbScriptFile, sourceSpreadsheetFile);
        ExcelDocument doc = new ExcelDocument(sourceSpreadsheetFile);
        doc.runScript(vbScriptFile);
        log.info("Running of VB script finished successfully.");

        log.info("Save changes to '{}'.", outputSpreadsheetFile);
        doc.saveAs(outputSpreadsheetFile);

        log.info("Excel document is saved successfully.");
    }
```

See the full source of this example for more details or check following instructions to run it.

### Running

>:warning: **To be able to build and run this example it's necessary to have an access
>to some instance of EasyRPA Control Server.**

Its a fully workable process. To play around with it and run do the following:
1. Download this example using [link][down_git_link].
2. Unpack it somewhere on local file system.
3. Specify URL to the available instance of EasyRPA Control Server in the `pom.xml` of this example:
    ```xml
    <repositories>
        <repository>
            <id>easy-rpa-repository</id>
            <url>[Replace with EasyRPA Control Server URL]/nexus/repository/easyrpa/</url>
        </repository>
    </repositories>
    ```
4. If necessary, change version of `easy-rpa-engine-parent` in the same `pom.xml` to corresponding version of
   EasyRPA Control Server:
    ```xml
    <parent>
        <groupId>eu.ibagroup</groupId>
        <artifactId>easy-rpa-engine-parent</artifactId>
        <version>[Replace with version of EasyRPA Control Server]</version>
    </parent>
    ```

5. Build it using `mvn clean install` command. This command should be run within directory of this example.
6. Run `main()` method of `CustomVbsRunningModule` class.

[down_git_link]: https://downgit.github.io/#/home?url=https://github.com/easyrpa/openframework/tree/main/examples/excel/custom-vbs-running


## Configuration
All necessary configuration files can be found in <code>src/main/resources</code> directory.

**apm_run.properties**

| Parameter     | Value         |
| ------------- |---------------|
| `source.spreadsheet.file` | Path to source spreadsheet file. It can be path on local file system or within resources of this project. |
| `vb.script.file` | Path to VBS file that contains script to run. It can be path on local file system or within resources of this project. |