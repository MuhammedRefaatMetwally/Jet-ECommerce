import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.dmoral.toasty.Toasty

@Composable
fun ErrorToast() {

    val ctx = LocalContext.current

        Button(
            // on below line we are adding width
            // for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            onClick = {

                Toasty.error(ctx, "This is an error toast.", Toast.LENGTH_SHORT, true).show()
            }) {

            Text(text = "Error Toast")
        }


    }

