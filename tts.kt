import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tts : TextToSpeech
    lateinit var text : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById<EditText>(R.id.et)

        var b = findViewById<Button>(R.id.btn)
        b.setOnClickListener {
            if (text.text.toString() == "") {
                Toast.makeText(baseContext,"ENTER SOME TEXT", Toast.LENGTH_SHORT).show()
            } else {
                texttospeech()
            }
        }

    }

    private fun texttospeech() {

        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener{
            if(it == TextToSpeech.SUCCESS)
            {
                tts.language = Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(text.text.toString(),TextToSpeech.QUEUE_ADD,null )
            }
        })
    }

    override fun onDestroy() {
        if(tts == null)
        {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}