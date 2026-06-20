package io.github.johnsafwat.kanimatedbutton

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun KAnimatedButton(
    buttonType: KAnimatedButtonType = KAnimatedButtonType.LARGE_RECTANGLE,
    modifier: Modifier = Modifier,
    title: String? = null,
    padding: Float? = null,
    onClick: () -> Unit,
    isEnabled: Boolean = false,
    isLoading: Boolean = false,
    maxSize: Float? = null,
    minSize: Float? = null,
    borderRadius: Float? = null,
    elevation: Float? = null,
    textStyle: TextStyle? = null,
    color: ButtonColors? = null,
    borderColor: Color = KAnimatedButtonDefaults.Primary,
    height: Float? = null,
    wrapContentWidth: Boolean = false,
    child: (@Composable () -> Unit)? = null,
) {
    // resolved params (avoid shadowing)
    val contentPadding = padding ?: buttonType.padding
    val resolvedMaxSize =
        maxSize ?: LocalWindowInfo.current.containerSize.width
            .toFloat()
    val resolvedMinSize = minSize ?: getMinimumSize(buttonType)
    val resolvedBorderRadius = borderRadius ?: buttonType.shape
    val resolvedElevation = ButtonDefaults.buttonElevation((elevation ?: buttonType.elevation).dp)
    val resolvedTextStyle =
        textStyle
            ?: if (buttonType == KAnimatedButtonType.LARGE_RECTANGLE || buttonType == KAnimatedButtonType.LARGE_ROUNDED) MaterialTheme.typography.labelSmall else MaterialTheme.typography.bodyMedium
    val buttonColors = color ?: buttonType.buttonColors

    // apply height to modifier (reassign)
    var baseModifier = modifier
    if (height != null) {
        baseModifier = baseModifier.height(height.dp)
    }

    val foregroundColor =
        if (isEnabled) {
            if (buttonType.buttonKind == ButtonKind.OUTLINED) {
                KAnimatedButtonDefaults.Primary
            } else {
                KAnimatedButtonDefaults.OnPrimary
            }
        } else {
            KAnimatedButtonDefaults.DisabledContent
        }

    // button animations setup (still used when not wrapping)
    val animatedTargetSize by animateFloatAsState(
        targetValue = if (isLoading) resolvedMinSize else resolvedMaxSize,
        animationSpec =
            if (isLoading) {
                keyframes {
                    durationMillis = 600
                    resolvedMaxSize at 0
                    (resolvedMinSize - 20) at 400
                    resolvedMinSize at 600
                }
            } else {
                keyframes {
                    durationMillis = 600
                    resolvedMinSize at 0
                    resolvedMinSize + 100 at 100
                    resolvedMaxSize at 600
                }
            },
    )

    val buttonBorderRadius by animateDpAsState(
        targetValue = if (isLoading) (100).dp else resolvedBorderRadius.dp,
        animationSpec =
            tween(
                durationMillis = 300,
                easing = EaseInOutCubic,
            ),
    )

    // Build final modifier depending on wrapContentWidth and loading state:
    // - If wrapContentWidth == true:
    //     - when loading -> force width to minimum (so it becomes circular)
    //     - when not loading -> no fixed width, so content defines width (use animateContentSize to animate size changes)
    // - If wrapContentWidth == false (default behavior):
    //     - use animatedTargetSize as width (existing full-width animation behavior)
    val finalModifier = if (wrapContentWidth) {
        if (isLoading) {
            baseModifier
                .width(resolvedMinSize.dp)
                .animateContentSize(
                    animationSpec =
                        spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessMedium,
                        ),
                )
        } else {
            baseModifier.animateContentSize(
                animationSpec =
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
            )
        }
    } else {
        baseModifier
            .width(animatedTargetSize.dp)
            .animateContentSize(
                animationSpec =
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
            )
    }

    Button(
        onClick = {
            if (isLoading) return@Button
            onClick()
        },
        enabled = isEnabled,
        border =
            if (buttonType.buttonKind == ButtonKind.OUTLINED) {
                BorderStroke(
                    width = 1.dp,
                    color = borderColor,
                )
            } else {
                null
            },
        shape = RoundedCornerShape(buttonBorderRadius),
        elevation = resolvedElevation,
        modifier = finalModifier,
        colors = buttonColors,
        contentPadding = PaddingValues(contentPadding.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Animate content transition between loading and non-loading states
            AnimatedContent(
                targetState = isLoading,
                transitionSpec = {
                    fadeIn(
                        animationSpec =
                            tween(
                                durationMillis = 300,
                                delayMillis = 100,
                                easing = EaseInOutCubic,
                            ),
                    ) togetherWith
                        fadeOut(
                            animationSpec =
                                tween(
                                    durationMillis = 300,
                                    easing = EaseInOutCubic,
                                ),
                        )
                },
            ) { isLoadingState ->
                if (!isLoadingState) {
                    // Display custom child or title when not loading
                    if (child != null) {
                        child()
                    } else if (title != null) {
                        Text(
                            text = title,
                            style =
                                resolvedTextStyle.copy(
                                    color = foregroundColor,
                                ),
                        )
                    }
                } else {
                    // Display loading indicator
                    CircularProgressIndicator(
                        color = foregroundColor,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

fun getMinimumSize(buttonType: KAnimatedButtonType): Float =
    when (buttonType) {
        KAnimatedButtonType.LARGE_RECTANGLE -> 56f
        KAnimatedButtonType.LARGE_ROUNDED -> 56f
        KAnimatedButtonType.MEDIUM_RECTANGLE -> 40f
        KAnimatedButtonType.SMALL_RECTANGLE -> 40f
        KAnimatedButtonType.OUTLINE_SMALL_RECTANGLE -> 40f
        KAnimatedButtonType.OUTLINE_LARGE_ROUNDED -> 56f
        KAnimatedButtonType.OUTLINE_LARGE_RECTANGLE -> 56f
        KAnimatedButtonType.OUTLINED_MEDIUM_RECTANGLE -> 40f
    }
