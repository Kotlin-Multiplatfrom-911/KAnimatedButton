package io.github.johnsafwat.kanimatedbutton

import androidx.compose.material3.ButtonColors

enum class KAnimatedButtonType(
    val padding: Float,
    val shape: Float,
    val elevation: Float,
    val buttonColors: ButtonColors,
    val buttonKind: ButtonKind,
) {
    LARGE_ROUNDED(
        16f,
        1000f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Primary,
            contentColor = KAnimatedButtonDefaults.OnPrimary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.FILLED,
    ),

    LARGE_RECTANGLE(
        16f,
        16f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Primary,
            contentColor = KAnimatedButtonDefaults.OnPrimary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.FILLED,
    ),

    MEDIUM_RECTANGLE(
        8f,
        8f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Primary,
            contentColor = KAnimatedButtonDefaults.OnPrimary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.FILLED,
    ),

    SMALL_RECTANGLE(
        4f,
        4f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Primary,
            contentColor = KAnimatedButtonDefaults.OnPrimary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.FILLED,
    ),

    OUTLINE_SMALL_RECTANGLE(
        4f,
        4f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Surface,
            contentColor = KAnimatedButtonDefaults.Primary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.OUTLINED,
    ),

    OUTLINE_LARGE_ROUNDED(
        16f,
        1000f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Surface,
            contentColor = KAnimatedButtonDefaults.Primary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.OUTLINED,
    ),

    OUTLINE_LARGE_RECTANGLE(
        16f,
        16f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Surface,
            contentColor = KAnimatedButtonDefaults.Primary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.OUTLINED,
    ),

    OUTLINED_MEDIUM_RECTANGLE(
        8f,
        8f,
        0f,
        ButtonColors(
            containerColor = KAnimatedButtonDefaults.Surface,
            contentColor = KAnimatedButtonDefaults.Primary,
            disabledContentColor = KAnimatedButtonDefaults.DisabledContent,
            disabledContainerColor = KAnimatedButtonDefaults.DisabledContainer,
        ),
        buttonKind = ButtonKind.OUTLINED,
    ),
}
