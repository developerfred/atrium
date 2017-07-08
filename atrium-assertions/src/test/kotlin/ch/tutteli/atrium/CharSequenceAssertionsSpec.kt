package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty

object CharSequenceAssertionsSpec : Spek({
    val text = "hello my name is robert"
    val fluent = assert(text)

    val containsProp: KProperty<CharSequenceContainsBuilder> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction1<CharSequence, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val exactly = CharSequenceContainsBuilder::exactly.name

    describe("fun $contains and $containsNot") {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$contains 'hello' throws AssertionError") {
                expect {
                    fluentEmptyString.contains("hello")
                }.toThrow<AssertionError>().and.message.contains(CONTAINS)
            }
            test("$contains 'hello' $exactly once throws AssertionError") {
                expect {
                    fluentEmptyString.contains.exactly(1).values("hello")
                }.toThrow<AssertionError>().and.message.contains(String.format(EXACTLY_TIME.getDefault(), 1) + ": 0")
            }
            test("$contains 'hello' $exactly twice throws AssertionError") {
                expect {
                    fluentEmptyString.contains.exactly(2).values("hello")
                }.toThrow<AssertionError>().and.message.contains(String.format(EXACTLY_TIMES.getDefault(), 2) + ": 0")
            }
            test("$containsNot 'hello' does not throw") {
                fluentEmptyString.containsNot("hello")
            }
        }

        context("text '$text'") {
            context("search for 'hello' and 'robert'") {
                test("$contains 'hello' does not throw") {
                    fluent.contains("hello")
                }
                test("$contains 'hello' $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello")
                }
                test("$contains 'hello' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello")
                    }.toThrow<AssertionError>().and.message.contains(CONTAINS_NOT)
                }

                test("$contains 'hello' and 'robert' does not throw") {
                    fluent.contains("hello", "robert")
                }
                test("$contains 'hello' and 'robert' $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello", "robert")
                }
                test("$contains 'hello' and 'robert' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello", "robert")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "robert")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'hello' and 'robert' as Any does not throw") {
                    fluent.contains("hello" as Any, "robert" as Any)
                }
                test("$contains 'hello' and 'robert' as Any $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello" as Any, "robert" as Any)
                }
                test("$contains 'hello' and 'robert' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello" as Any, "robert" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' and 'robert' as Any throws AssertionError") {
                    expect {
                        fluent.containsNot("hello" as Any, "robert" as Any)
                    }.toThrow<AssertionError>()
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                test("$contains 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNot("notInThere", "neitherInThere")
                }

                test("$contains 'notInThere' and 'neitherInThere' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>().and.message.contains(String.format(EXACTLY_TIME.getDefault(), 1))
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'neitherInThere' as Any does not throw") {
                    fluent.containsNot("notInThere" as Any, "neitherInThere" as Any)
                }
            }

            context("search for 'hello' and 'notInThere'") {
                test("$contains 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' does not throw") {
                    fluent.containsNot("notInThere")
                }

                test("$contains 'hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'notInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'notInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'notInThere' and 'hello' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'hello' as Any $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'hello' as Any $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'hello' as Any throws AssertionError") {
                    expect {
                        fluent.containsNot("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
            }
        }

        context("fun $contains with specifier $exactly") {
            it("throws an ${IllegalArgumentException::class.simpleName} for exactly -1") {
                expect {
                    fluent.contains.exactly(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
            }
            it("throws an ${IllegalArgumentException::class.simpleName} for exactly 0") {
                expect {
                    fluent.contains.exactly(0)
                }.toThrow<IllegalArgumentException>().and.message.contains(containsNot)
            }

            context("text 'hello world'") {
                val fluentHelloWorld = assert("hello world")

                test("$contains 'h' and 'e' and 'w' exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).values("h", "e", "w")
                }

                test("$contains 'h' and 'e' and 'w' exactly 4 times throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(4).values("h", "e", "w")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'o' exactly twice does not throw") {
                    fluentHelloWorld.contains.exactly(2).values("o")
                }

                test("$contains 'o' exactly 4 times throws AssertionError and message contains both, how many times we expected (4) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(4).values("o")
                    }.toThrow<AssertionError>().and.message.contains(String.format(EXACTLY_TIMES.getDefault(), 4) + ": 2")
                }

                test("$contains 'l' exactly 3 times does not throw") {
                    fluentHelloWorld.contains.exactly(3).values("l")
                }

                test("$contains 'o' and 'l' exactly 3 times throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(3).values("o", "l")
                    }.toThrow<AssertionError>().and.message.contains(String.format(EXACTLY_TIMES.getDefault(), 3) + ": 2")
                }
            }
        }
    }


    describe("fun ${fluent::isEmpty.name} and ${fluent::isNotEmpty.name}") {
        context("string is empty") {
            test("${fluent::isEmpty.name} does not throw") {
                assert("").isEmpty()
                assert(StringBuilder()).isEmpty()
                assert(StringBuffer()).isEmpty()
            }
            test("${fluent::isNotEmpty.name} throws an AssertionError") {
                expect {
                    assert("").isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuilder()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuffer()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("${fluent::isEmpty.name} throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuilder(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuffer(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
            }
            test("${fluent::isNotEmpty.name} does not throw") {
                assert(notEmptyString).isNotEmpty()
                assert(StringBuilder(notEmptyString)).isNotEmpty()
                assert(StringBuffer(notEmptyString)).isNotEmpty()
            }
        }
    }

    describe("fun ${fluent::startsWith.name} and ${fluent::startsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::startsWith.name} 'hello' does not throw") {
                fluent.startsWith("hello")
            }
            test("${fluent::startsNotWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWith("hello")
                }.toThrow<AssertionError>().and.message.contains(STARTS_NOT_WITH)
            }

            test("${fluent::startsWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.startsWith("goodbye")
                }.toThrow<AssertionError>().and.message.contains(STARTS_WITH)
            }
            test("${fluent::startsNotWith.name} 'robert' does not throw") {
                fluent.startsNotWith("goodbye")
            }
        }
    }

    describe("fun ${fluent::endsWith.name} and ${fluent::endsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::endsWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.endsWith("hello")
                }.toThrow<AssertionError>().and.message.contains(ENDS_WITH)
            }
            test("${fluent::endsNotWith.name} 'hello' does not throw") {
                fluent.endsNotWith("hello")
            }

            test("${fluent::endsWith.name} 'robert' does not throw") {
                fluent.endsWith("robert")
            }
            test("${fluent::endsNotWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWith("robert")
                }.toThrow<AssertionError>().and.message.contains(ENDS_NOT_WITH)
            }
        }
    }
})
