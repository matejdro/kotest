package io.kotest.property.arbitrary

import io.kotest.property.Arb
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.Period
import java.time.YearMonth
import java.time.ZonedDateTime
import java.util.Date
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST")
@Deprecated("This logic has moved to ArbResolver and this function will be removed in 5.6. Since 5.5")
actual inline fun <reified A> targetDefaultForClass(): Arb<A>? = targetDefaultForType(type = typeOf<A>()) as Arb<A>?

fun targetDefaultForType(providedArbs: Map<KClass<*>, Arb<*>> = emptyMap(), type: KType): Arb<*>? {
   when (type) {
      typeOf<Instant>(), typeOf<Instant?>() -> Arb.instant()
      typeOf<Date>(), typeOf<Date?>() -> Arb.javaDate()
      typeOf<LocalDate>(), typeOf<LocalDate?>() -> Arb.localDate()
      typeOf<LocalDateTime>(), typeOf<LocalDateTime?>() -> Arb.localDateTime()
      typeOf<LocalTime>(), typeOf<LocalTime?>() -> Arb.localTime()
      typeOf<Period>(), typeOf<Period?>() -> Arb.period()
      typeOf<YearMonth>(), typeOf<YearMonth?>() -> Arb.yearMonth()
      typeOf<ZonedDateTime>(), typeOf<ZonedDateTime?>() -> Arb.zonedDateTime()
      typeOf<OffsetDateTime>(), typeOf<OffsetDateTime?>() -> Arb.offsetDateTime()
      typeOf<BigDecimal>(), typeOf<BigDecimal?>() -> Arb.bigDecimal()
      typeOf<BigInteger>(), typeOf<BigInteger?>() -> Arb.bigInt(maxNumBits = 256)
      else -> null
   }?.let { return it }

   val clazz = type.classifier as? KClass<*> ?: return null
   return when {
      clazz.isSubclassOf(List::class) -> {
         val upperBound = type.arguments.first().type ?: error("No bound for List")
         Arb.list(Arb.forType(providedArbs, upperBound) as Arb<*>)
      }
      clazz.isSubclassOf(Set::class) -> {
         val upperBound = type.arguments.first().type ?: error("No bound for Set")
         val upperBoundKClass = (upperBound.classifier as? KClass<*>)
         if (upperBoundKClass != null && upperBoundKClass.isSubclassOf(Enum::class)) {
            val maxElements = Class.forName(upperBoundKClass.java.name).enumConstants.size
            Arb.set(Arb.forType(providedArbs, upperBound) as Arb<*>, 0..maxElements)
         } else if(upperBoundKClass != null && upperBoundKClass.isSealed) {
            val maxElements = upperBoundKClass.sealedSubclasses.size
            Arb.set(Arb.forType(providedArbs, upperBound) as Arb<*>, 0..maxElements)
         } else {
            Arb.set(Arb.forType(providedArbs, upperBound) as Arb<*>)
         }
      }
      clazz.isSubclassOf(Pair::class) -> {
         val first = type.arguments[0].type ?: error("No bound for first type parameter of Pair")
         val second = type.arguments[1].type ?: error("No bound for second type parameter of Pair")
         Arb.pair(Arb.forType(providedArbs, first)!!, Arb.forType(providedArbs, second)!!)
      }
      clazz.isSubclassOf(Map::class) -> {
         // map key type can have or have not variance
         val first = type.arguments[0].type ?: error("No bound for first type parameter of Map<K, V>")
         val second = type.arguments[1].type ?: error("No bound for second type parameter of Map<K, V>")
         Arb.map(Arb.forType(providedArbs, first)!!, Arb.forType(providedArbs, second)!!)
      }
      clazz.isSubclassOf(Enum::class) -> {
         Arb.of(Class.forName(clazz.java.name).enumConstants.map { it as Enum<*> })
      }
      clazz.objectInstance != null -> Arb.constant(clazz.objectInstance!!)
      clazz.isSealed -> {
         Arb.choice(clazz.sealedSubclasses.map { subclass ->
            subclass.objectInstance?.let { Arb.constant(it) } ?: Arb.forClassUsingConstructor(providedArbs, subclass)
         })
      }
      else -> {
        Arb.forClassUsingConstructor(providedArbs, clazz)
      }
   }
}

// need some supertype that types a type param so it gets baked into the class file
abstract class TypeReference<T> : Comparable<TypeReference<T>> {
   // this is the type of T
   val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

   override fun compareTo(other: TypeReference<T>) = 0
}
