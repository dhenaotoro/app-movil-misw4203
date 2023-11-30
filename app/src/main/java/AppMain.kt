import android.app.Activity
import com.example.app_movil_misw4203.model.database.AppDatabase

class AppMain : Activity() {
    val database by lazy { AppDatabase.getDatabase(this) }
}