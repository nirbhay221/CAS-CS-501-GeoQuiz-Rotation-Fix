import androidx.lifecycle.SavedStateHandle
import com.bignerdranch.android.geoquiz.CURRENT_INDEX_KEY
import com.bignerdranch.android.geoquiz.QuizViewModel
import org.junit.Assert.assertEquals
import com.bignerdranch.android.geoquiz.R
import org.junit.Before
import org.junit.Test

class QuizViewModelTest {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var quizViewModel: QuizViewModel

    @Before
    fun setUp() {
        savedStateHandle = SavedStateHandle()
        quizViewModel = QuizViewModel(savedStateHandle)
    }

    @Test
    fun initiallyProvidesFirstQuestionText() {
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun wrapsAroundQuestionBank() {
        savedStateHandle.set(CURRENT_INDEX_KEY, 5)
        quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun isCheaterInitiallyFalse() {
        assertEquals(false, quizViewModel.isCheater)
    }

    @Test
    fun isCheaterSetCorrectly() {
        quizViewModel.isCheater=true
        assertEquals(true, quizViewModel.isCheater)


        quizViewModel.moveToNext()

        assertEquals(false, quizViewModel.isCheater)
    }
}