package collector

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths

fun main(args: Array<String>) {
    val parser = CmdLineParser(Args)
    try {
        parser.parseArgument(*args)
    } catch (e: CmdLineException) {
        System.err.println(e.message)
        parser.printUsage(System.err)
    }

    val cfg = Configuration(Configuration.VERSION_2_3_31)
    cfg.setClassForTemplateLoading(Args::class.java, "/templates")
    cfg.defaultEncoding = "UTF-8"
    cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    cfg.logTemplateExceptions = false
    cfg.wrapUncheckedExceptions = true
    cfg.fallbackOnNullLoopVariable = false

    val root = mutableMapOf<Any?, Any?>()
    root["Args"] = Args
    root["pHarness"] = Args.pHarness
    root["pFuzzRes"] = Args.pFuzzRes
    root["pJBWJ"] = Args.pJBWJ
    root["pOut"] = Args.pOut
    root["pSE"] = Args.pSE

    val template = cfg.getTemplate("collector.ftlh")

    createExFileBat(template, root)
}

fun createExFileBat(template: Template, root: MutableMap<Any?, Any?>) {
    val projectDirAbsolutePath = Paths.get(".").toAbsolutePath().toString()
    val exBatFilePath = Paths.get(projectDirAbsolutePath, "GraAFLCollector.bat")

    val exBatFile = File(exBatFilePath.toUri())
    if (exBatFile.exists()) exBatFile.delete()

    val file = exBatFilePath.toFile()
    FileWriter(file).use { out ->
        template.process(root, out)
    }
}
