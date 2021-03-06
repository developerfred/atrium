package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Map].
 */
enum class DescriptionResultAssertion(override val value: String) : StringBasedTranslatable {
    IS_NOT_SUCCESS("❗❗ ist kein Success"),
    VALUE("Wert"),
    IS_NOT_FAILURE("❗❗ ist kein Fehler"),
    EXCEPTION("Exception")
}
