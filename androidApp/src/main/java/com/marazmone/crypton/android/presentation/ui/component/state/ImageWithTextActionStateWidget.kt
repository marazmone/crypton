package com.marazmone.crypton.android.presentation.ui.component.state

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors

@Composable
fun ImageWithTextActionStateWidget(
    @DrawableRes resId: Int,
    text: String,
    onActionRepeat: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp),
            textAlign = TextAlign.Center,
        )
        onActionRepeat?.also { action ->
            Button(
                onClick = action,
                colors = Colors.Button.DefaultButton,
            ) {
                Text(
                    text = stringResource(id = R.string.repeat),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ImageWithTextActionStateWidget_Preview_Error() {
    AppTheme {
        ImageWithTextActionStateWidget(
            resId = R.drawable.im_error_state,
            text = stringResource(id = R.string.something_wrong)
        ) {
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ImageWithTextActionStateWidget_Preview_Empty() {
    AppTheme {
        ImageWithTextActionStateWidget(
            resId = R.drawable.im_empty_state,
            text = stringResource(id = R.string.favorite_empty_state)
        )
    }
}
