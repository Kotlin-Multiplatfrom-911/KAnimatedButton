package io.github.johnsafwat.kanimatedbutton

import androidx.compose.ui.graphics.Color

/**
 * Default, brand-neutral colors used by [KAnimatedButton] and [KAnimatedButtonType].
 *
 * Every color is overridable at the call site (via the `color`/`borderColor` parameters
 * of [KAnimatedButton] or by passing a custom [KAnimatedButtonType]), so these values are
 * only the out-of-the-box defaults.
 */
object KAnimatedButtonDefaults {
    /** Primary accent color used for filled containers and outlined content/borders. */
    val Primary: Color = Color(0xFF3B82F6)

    /** Content color rendered on top of the [Primary] container. */
    val OnPrimary: Color = Color(0xFFFFFFFF)

    /** Surface color used as the container for outlined buttons. */
    val Surface: Color = Color(0xFFFFFFFF)

    /** Content color used when a button is disabled. */
    val DisabledContent: Color = Color(0xFF9E9E9E)

    /** Container color used when a button is disabled. */
    val DisabledContainer: Color = Color(0xFFE0E0E0)
}
