package kt.mobile.spotify_stats.feature_search.presentation.composables

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.feature_search.presentation.SearchEvent
import kt.mobile.spotify_stats.feature_search.presentation.SearchViewModel

@Composable
fun SearchTopBar(
    viewModel: SearchViewModel,
    onNavigateUp: () -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_icon)
                )
            }
        },
        actions = {
            IconButton(onClick = { viewModel.onEvent(SearchEvent.Query("")) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.clear_text_icon)
                )
            }
        }, title = {
            BasicTextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = viewModel.searchState.value.query,
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                cursorBrush = SolidColor(Color.White),
                maxLines = 1,
                onValueChange = { viewModel.onEvent(SearchEvent.Query(it)) },
            )
        }
    )
}