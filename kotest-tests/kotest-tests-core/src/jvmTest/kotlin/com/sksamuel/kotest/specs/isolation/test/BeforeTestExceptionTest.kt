package com.sksamuel.kotest.specs.isolation.test

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.*
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.core.test.TestStatus
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.runner.jvm.TestEngineListener
import io.kotest.runner.jvm.spec.SpecExecutor

internal class BehaviorSpecWithBeforeTestError : BehaviorSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   given("given") {
      When("when") {
         then("then") {
         }
      }
   }
})

internal class FunSpecWithBeforeTestError : FunSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   test("fun spec") {}
})

internal class StringSpecWithBeforeTestError : StringSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   "string test"{}
})

internal class ShouldSpecWithBeforeTestError : ShouldSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   should("foo") {}
})

internal class DescribeSpecWithBeforeTestError : DescribeSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
})

internal class FeatureSpecWithBeforeTestError : FeatureSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   feature("feature") {
      scenario("scenario") { }
   }
})

internal class ExpectSpecWithBeforeTestError : ExpectSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
})

internal class FreeSpecWithBeforeTestError : FreeSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   "test" {}
})

internal class WordSpecWithBeforeTestError : WordSpec({
   isolation = IsolationMode.InstancePerTest
   beforeTest {
      error("boom")
   }
   "this test" should {
      "be alive" {}
   }
})

class BeforeTestExceptionTest : WordSpec({

   var error: Throwable? = null

   val listener = object : TestEngineListener {
      override fun testFinished(testCase: TestCase, result: TestResult) {
         if (result.status == TestStatus.Error)
            error = result.error
      }
   }

   "an exception in before test" should {
      "fail the test for behavior spec" {
         val executor = SpecExecutor(listener)
         executor.execute(BehaviorSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for feature spec" {
         val executor = SpecExecutor(listener)
         executor.execute(FeatureSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for word spec" {
         val executor = SpecExecutor(listener)
         executor.execute(WordSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for should spec" {
         val executor = SpecExecutor(listener)
         executor.execute(ShouldSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for string spec" {
         val executor = SpecExecutor(listener)
         executor.execute(StringSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for describe spec" {
         val executor = SpecExecutor(listener)
         executor.execute(DescribeSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for free spec" {
         val executor = SpecExecutor(listener)
         executor.execute(FreeSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for fun spec" {
         val executor = SpecExecutor(listener)
         executor.execute(FunSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
      "fail the test for expect spec" {
         val executor = SpecExecutor(listener)
         executor.execute(ExpectSpecWithBeforeTestError::class)
         error.shouldBeInstanceOf<IllegalStateException>()
         error!!.shouldHaveMessage("boom")
      }
   }
})
