package ru.boronin.compose.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JoinButton(
  modifier: Modifier = Modifier,
  onClick: (Boolean) -> Unit = {}
) {
  var buttonState: JoinButtonState by remember { mutableStateOf(JoinButtonState.IDLE) }
  // Button shape
  val shape = RoundedCornerShape(corner = CornerSize(27.dp))
  // Button background
  val buttonBackgroundColor: Color by animateColorAsState(
    if (buttonState == JoinButtonState.PRESSED)
      Color.White
    else
      Color.Blue
  )
  // Animate size
  val buttonSize: Int by animateIntAsState(targetValue =
    if (buttonState == JoinButtonState.PRESSED)
      124
    else
      100
  )
  // Animate background - second way
  val transition = updateTransition(
    targetState = buttonState,
    label = "JoinButtonTransition"
  )
  val duration = 600
  val buttonBackgroundColor2: Color by transition.animateColor(
    transitionSpec = { tween(duration) },
    label = "Button Background Color"
  ) { state ->
    when (state) {
      JoinButtonState.IDLE -> Color.Blue
      JoinButtonState.PRESSED -> Color.White
    }
  }
  // Button icon
  val iconAsset: ImageVector =
    if (buttonState == JoinButtonState.PRESSED)
      Icons.Default.Check
    else
      Icons.Default.Add
  val iconTintColor: Color =
    if (buttonState == JoinButtonState.PRESSED)
      Color.Blue
    else
      Color.White

  Box(
    modifier = modifier
      .clip(shape)
      .border(width = 1.dp, color = Color.Blue, shape = shape)
      .background(color = buttonBackgroundColor2)
      .size(width = buttonSize.dp, height = 74.dp)
      .clickable(onClick = {
        buttonState = if (buttonState == JoinButtonState.IDLE) {
          onClick.invoke(true)
          JoinButtonState.PRESSED
        } else {
          onClick.invoke(false)
          JoinButtonState.IDLE
        }
      }),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = iconAsset,
      contentDescription = "Plus Icon",
      tint = iconTintColor,
      modifier = Modifier.size(46.dp)
    )
  }
}

enum class JoinButtonState {
  IDLE,
  PRESSED
}

@Preview
@Composable
fun JoinButtonPreview() {
  JoinButton()
}
