package collector

import org.kohsuke.args4j.Option
import org.kohsuke.args4j.spi.BooleanOptionHandler
import java.nio.file.Paths


object Args {

    @Option(name = "-harness", usage = "PathToHarness", required = false)
    var pHarness: String = ".\\WinAFLHarness\\x64\\Release"

    @Option(name = "-se", usage = "PathToSorter", required = false)
    var pSE: String = ".\\SortExceptions\\build\\libs"

    @Option(name = "-jbwj", usage = "PathToJBWJ", required = false)
    var pJBWJ: String = ".\\JBWJ\\build\\libs"

    @Option(name = "-fr", usage = "PathToFuzzProjectResourcesWithDLL", required = false)
    var pFuzzRes: String = ".\\ImageTest\\build\\libs"

    @Option(name = "-o", usage = "outputDirectory", required = false)
    var pOut: String = ".\\winafl-build64\\bin\\Release"

}
