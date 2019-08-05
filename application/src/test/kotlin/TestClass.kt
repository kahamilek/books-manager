import org.junit.jupiter.api.Test
import java.io.File

class TestClass {

    @Test
    fun void(){
        val file = File("dlugieeeeee_imieeeeeee.csv")
        val fileContent = "askmdaosmdlanckjncjknaosjdoiajwdoiajwodijaowidjaoiwjdkasdlnzlzllzdknnnvjdnkjn"

        file.writeText(fileContent)
        println("Text was wrote")
        file.createNewFile()
        println("file created")
        Thread.sleep(2000)
        file.delete()
        println("File deleted")
        Thread.sleep(2000)
    }
}