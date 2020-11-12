package br.com.isaias.calourouninorte.utils


fun CharSequence?.isFilled(): Boolean = !(isNullOrEmpty() || isNullOrBlank())

fun CharSequence.isValidCardNumber() = matches(CARD_NUMBER_REGEX)

fun CharSequence.isValidCep() = matches(CEP_REGEX)

fun CharSequence.isValidCpfCnpjNumber() = isValidCpfNumber() || matches(CNPJ_REGEX)

fun CharSequence.isValidCpfNumber(): Boolean {
    if (notMatchesAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

    val sanitizedInput = removeNotDigits()

    if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

    fun mod11CheckDigit(digits: List<Int>): Int {
        require(digits.size in 9..10)

        val mod11 = digits.reversed().mapIndexed { index, digit ->
            val weight = index + 2
            digit * weight
        }.sum() % 11
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()
    val expectedFirstCheckDigit = mod11CheckDigit(sanitizedInput.take(9).digits())

    if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

    val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()
    val expectedSecondCheckDigit = mod11CheckDigit(sanitizedInput.take(10).digits())

    if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

    return true
}

fun CharSequence.isValidUf() = length == 2

fun CharSequence.isValidPhoneNumber() = matches(Regex("""^\(\d{2}\) 9[6-9]\d{3}-\d{4}$"""))

fun CharSequence.isValidSerialNumber(): Boolean = matches(Regex("""^8\d{47}$""")) ||
        matches(Regex("""^[123456790]\d{46}$""")) ||
        matches(Regex("""^8\d{11}\s\d{12}\s\d{12}\s\d{12}$""")) ||
        matches(Regex("""^[123456790]\d{4}.\d{5}\s\d{5}.\d{6}\s\d{5}.\d{6}\s\d\s\d{14}$"""))

private fun Char.toNumericValue() = Character.getNumericValue(this)

private fun CharSequence.notMatchesAny(vararg regex: Regex) = !regex.any { matches(it) }

private fun CharSequence.removeNotDigits() = replace(Regex("""\D"""), "")

private fun CharSequence.hasRepeatedDigits(numberOfRepeatedDigits: Int = 1) =
    contains(Regex("""(\d)\1{$numberOfRepeatedDigits}"""))

private fun String.digits() = map(Character::getNumericValue)

private val CEP_REGEX = Regex("""^\d{5}-\d{3}$""")
private val CARD_NUMBER_REGEX = Regex("""^\d{4}\s\d{4}\s\d{4}\s\d{4}$""")
private val CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")

private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")

private const val MAXIMUM_REPEATED_DIGITS = 10

private const val FIRST_CHECK_DIGIT_INDEX = 9
private const val SECOND_CHECK_DIGIT_INDEX = 10
