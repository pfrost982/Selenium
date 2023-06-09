package ads_std

fun leadingZerosString(number: Int, zeros: Int): String = String.format("%0${zeros}d", number)