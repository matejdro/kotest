package io.kotlintest.properties

import com.sksamuel.koors.Random

interface Generator<T> {
  fun generate(): T

  companion object {

    fun choose(min: Int, max: Int): Generator<Int> = object : Generator<Int> {
      override fun generate(): Int = Random.default.nextInt(max - min) + min
    }

    fun choose(min: Long, max: Long): Generator<Long> = object : Generator<Long> {
      override fun generate(): Long = (Random.default.nextLong() + min) % max
    }

    fun <T> oneOf(values: List<T>): Generator<T> = object : Generator<T> {
      override fun generate(): T = Random.default.shuffle(values).first()
    }

    fun string(): Generator<String> = object : Generator<String> {
      override fun generate(): String = nextPrintableString(Random.default.nextInt(100))
    }

    fun bool() = object : Generator<Boolean> {
      override fun generate(): Boolean = Random.default.nextBoolean()
    }

    fun double() = object : Generator<Double> {
      override fun generate(): Double = Random.default.nextDouble()
    }

    fun float() = object : Generator<Float> {
      override fun generate(): Float = Random.default.nextFloat()
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> default(): Generator<T> {
      return when (T::class) {
        String::class -> Generator.string() as Generator<T>
        Int::class -> Generator.choose(Integer.MIN_VALUE, Integer.MAX_VALUE) as Generator<T>
        Long::class -> Generator.choose(Long.MIN_VALUE, Long.MAX_VALUE) as Generator<T>
        Boolean::class -> Generator.bool() as Generator<T>
        Float::class -> Generator.float() as Generator<T>
        Double::class -> Generator.double() as Generator<T>
        else -> throw IllegalArgumentException("Cannot infer generator for ${T::class.simpleName}; specify generators explicitly")
      }
    }
  }

  fun nextString(length: Int): String {
    fun safeChar(): Char {
      val surrogateStart: Int = 0xD800
      val res = Random.default.nextInt(surrogateStart - 1) + 1
      return res.toChar()
    }
    return (0..length).map { safeChar() }.joinToString("")
  }

  fun nextPrintableString(length: Int): String {
    return (0..length).map { Random.Companion.default.nextPrintableChar() }.joinToString("")
  }
}