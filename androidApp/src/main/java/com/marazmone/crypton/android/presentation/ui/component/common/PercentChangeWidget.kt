package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Rate.Down
import com.marazmone.crypton.android.presentation.ui.Colors.Rate.Up
import com.marazmone.crypton.utils.amountWithCurrency

private const val RateDownAngle = 180f
private const val RateTopAngle = 0f

@Composable
fun PercentChangeWidget(
    percentChange: Float,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {
    val rateColor = if (percentChange < 0f) Down else Up
    val rateRotate = if (percentChange < 0f) RateDownAngle else RateTopAngle
    val formattedText = percentChange.amountWithCurrency("%")
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_percent_change),
            contentDescription = null,
            modifier = Modifier
                .size(8.dp)
                .rotate(rateRotate),
            tint = rateColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = formattedText,
            style = textStyle.copy(color = rateColor)
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PercentChangeWidget_UP_Preview() {
    AppTheme {
        PercentChangeWidget(percentChange = 10.33f)
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PercentChangeWidget_DOWN_Preview() {
    AppTheme {
        PercentChangeWidget(percentChange = -10.22f)
    }
}
