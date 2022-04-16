package com.example.eldenmessage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {

    companion object {
        const val MEOW = "MessagesVM"
    }

    private val msgTemplates = mutableListOf(
        "**** ahead",
        "No **** ahead",
        "**** required ahead",
        "Be wary of ****",
        "Try ****",
        "Likely ****",
        "First off, ****",
        "Seek ****",
        "Still no ****...",
        "Why is it always ****?",
        "If only I had a ****...",
        "Didn't expect ****...",
        "Visions of ****...",
        "Could this be a ****?",
        "Time for ****",
        "****, O ****",
        "Behold, ****!",
        "Offer ****",
        "Praise the ****",
        "Let there be ****",
        "Ahh, ****...",
        "****",
        "****!",
        "****?",
        "****..."
    )

    private val msgConjunctions = mutableListOf(
        "And then ",
        "Or ",
        "But ",
        "In short ",
        "Except ",
        "By the way ",
        "So to speak ",
        "All the more ",
        ","
    )

    private val msgWords = mutableListOf(
        // [0] = Enemies
        mutableListOf(
            "enemy", "weak foe", "strong foe","monster","dragon","boss","sentry","group",
            "pack","decoy","undead","soldier","knight","cavalier","archer","sniper",
            "mage","ordinance","monarch","lord","demi-human","outsider","giant","horse",
            "dog","wolf","rat","beast","bird","raptor","snake","crab",
            "prawn","octopus","bug","scarab","slug","wraith","skeleton","monstrosity","ill-omened creature"
        ),
        // [1] = People
        mutableListOf(
            "Tarnished","warrior","swordfighter","knight","samurai","sorcerer","cleric","sage","merchant",
            "teacher","master","friend","lover","old dear","old codger","angel","fat coinpurse","pauper",
            "good sort","wicked sort","plump sort","skinny sort","lovable sort","pathetic sort","strange sort","nimble sort","laggardly sort",
            "invisible sort","unfathomable sort","giant sort","sinner","thief","liar","dastard","traitor","pair",
            "trio","noble","aristocrat","hero","champion","monarch","lord","god"
        ),
        // [2] = Things
        mutableListOf(
            "item","necessary item","precious item","something","something incredible","treasure chest","corpse","coffin","trap",
            "armament","shield","bow","projectile weapon","armor","talisman","skill","sorcery","incantation",
            "map","material","flower","grass","tree","fruit","seed","mushroom","tear",
            "crystal","butterfly","bug","dung","grace","door","key","ladder","lever",
            "lift","spiritspring","sending gate","stone astrolabe","Birdseye Telescope","message","bloodstain","Erdtree","Elden Ring"
        ),
        // [3] = Battle Tactics
        mutableListOf(
            "close-quarters battle","ranged battle","horseback battle","luring out",
            "defeating one-by-one","taking on all at once","rushing in","stealth",
            "mimicry","confusion","pursuit","fleeing",
            "summoning","circling around","jumping off","dashing through","brief respite"
        ),
        // [4] = Actions
        mutableListOf(
            "attacking","jump attack","running attack","critical hit","two-handing",
            "blocking","parrying","guard counter","sorcery","incantation",
            "skill","summoning","throwing","healing","running",
            "rolling","backstepping","jumping","crouching","target lock",
            "item crafting","gesturing"
        ),
        // [5] = Situations
        mutableListOf(
            "morning","noon","evening","night","clear sky","overcast","raing","storm","mist",
            "snow","patrolling","procession","crowd","surprise attack","ambush","pincer attack","beating to a pulp","battle",
            "reinforcements","ritual","explosion","high spot","defensible spot","climbable spot","bright spot","dark spot","open area",
            "cramped area","hiding place","sniping shot","recon spot","safety","danger","gorgeous view","detour","hidden path",
            "secret passage","shortcut","dead end","looking away","unnoticed","out of stamina"
        ),
        // [6] = Places
        mutableListOf(
            "high road","checkpoint","bridge","castle","fort","city",
            "ruins","church","tower","camp site","house","cemetery",
            "underground tomb","tunnel","cave","evergaol","great tree","cellar",
            "surface","underground","forest","river","lake","bog",
            "mountain","valley","cliff","waterside","nest","hole"
        ),
        // [7] = Directions
        mutableListOf(
            "east","west","south","north",
            "ahead","behind","left","right",
            "center","up","down","edge"
        ),
        // [8] = Body Parts
        mutableListOf(
            "head","stomach","back","arms",
            "legs","rump","tail","core",
            "fingers"
        ),
        // [9] = Affinities
        mutableListOf(
            "physical","standard","striking","slashing",
            "piercing","fire","lightning","magic",
            "holy","poison","toxic","scarlet rot",
            "blood loss","frost","sleep","madness",
            "death"
        ),
        // [10] = Concepts
        mutableListOf(
            "life","Death","light","dark","stars","fire","Order","chaos","joy","wrath","suffering","sadness",
            "comfort","bliss","misfortune","good fortune","bad luck","hope","despair","victory","defeat","research","faith","abundance",
            "rot","loyalty","injustice","secret","opportunity","pickle","clue","friendship","love","bravery","vigor","fortitude",
            "confidence","distracted","unguarded","introspection","regret","resignation","futility","on the brink","betrayal","revenge","destruction","recklessness",
            "calmness","vigilance","tranquility","sound","tears","sleep","depths","dregs","fear","sacrifice","ruin"

        ),
        // [11] = Phrases
        mutableListOf(
            "good luck","look carefully","listen carefully","well done","I did it!",
            "I've failed...","here!","not here!","don't you dare!","do it!","I can't take this...",
            "don't think","so lonely...","here again...","just getting started","stay calm","keep moving",
            "turn back","give up","don't give up","help me...","I don't believe it...","too high up",
            "I want to go home...","it's like a dream...","seems familiar...","beautiful...","you don't have the right","are you ready?"
        )
    )
    fun getConstantTemplates(): MutableList<String> {
        return msgTemplates
    }
    fun getConstantConjunctions(): MutableList<String> {
        return msgConjunctions
    }
    fun getWordsByCategory(category: Int): MutableList<String> {
        selectedWordCategory.value = category
        return msgWords[category]
    }


    var currentlySelecting: MutableLiveData<String> = MutableLiveData()
    var msgFormat: Boolean = false
    var selectedWordCategory: MutableLiveData<Int> = MutableLiveData(0)
    val msgTemplate1: MutableLiveData<String> = MutableLiveData("")
    val msgTemplate2: MutableLiveData<String> = MutableLiveData("")
    val msgConjunction: MutableLiveData<String> = MutableLiveData("")
    val msgWord1: MutableLiveData<String> = MutableLiveData("")
    val msgWord2: MutableLiveData<String> = MutableLiveData("")
    fun selectString(position: Int) {
        when (currentlySelecting.value) {
            "Template1" -> {
                msgTemplate1.value = msgTemplates[position]
            }
            "Template2" -> {
                msgTemplate2.value = msgTemplates[position]
            }
            "Conjunction" -> {
                msgConjunction.value = msgConjunctions[position]
            }
            "Word1" -> {
                msgWord1.value = msgWords[selectedWordCategory.value!!][position]
            }
            "Word2" -> {
                msgWord2.value = msgWords[selectedWordCategory.value!!][position]
            }
        }
    }

    fun isMessageComplete(): Boolean {
        return if (msgFormat) {
            !(msgWord1.value.isNullOrEmpty() || msgTemplate1.value.isNullOrEmpty() ||
                    msgWord2.value.isNullOrEmpty() || msgTemplate2.value.isNullOrEmpty() ||
                    msgConjunction.value.isNullOrEmpty()
                    )
        } else {
            !(msgWord1.value.isNullOrEmpty() || msgTemplate1.value.isNullOrEmpty())
        }
    }
    fun getFinalMessage(): String {
        var newMessage = ""
        if (!msgFormat) {
            newMessage = msgTemplate1.value!!
            Log.d(MEOW,"msgTemplate: $newMessage")

            val posOfFirstAsterisk = newMessage.indexOf("****")
            Log.d(MEOW,"posOfFirstAsterisk: $posOfFirstAsterisk")

            val stringPreAsterisk = newMessage.removeRange(posOfFirstAsterisk,newMessage.length)
            Log.d(MEOW,"stringPreAsterisk: $stringPreAsterisk")

            val stringPostAsterisk = newMessage.removeRange(0,posOfFirstAsterisk + 4)
            Log.d(MEOW,"stringPostAsterisk: $stringPostAsterisk")

            newMessage = stringPreAsterisk + msgWord1.value!! + stringPostAsterisk
            Log.d(MEOW,"finalMessage: $newMessage")
        }
        else {
            newMessage = msgTemplate1.value!!

            val posOfFirstAsterisk1 = newMessage.indexOf("****")
            Log.d(MEOW,"posOfFirstAsterisk1: $posOfFirstAsterisk1")

            val stringPreAsterisk1 = newMessage.removeRange(posOfFirstAsterisk1,newMessage.length)
            Log.d(MEOW,"stringPreAsterisk1: $stringPreAsterisk1")

            val stringPostAsterisk1 = newMessage.removeRange(0,posOfFirstAsterisk1 + 4)
            Log.d(MEOW,"stringPostAsterisk1: $stringPostAsterisk1")

            newMessage = stringPreAsterisk1 + msgWord1.value!! + stringPostAsterisk1
            Log.d(MEOW,"finalMessage1: $newMessage")

            if (msgConjunction.value!! == ",") {
                newMessage += ",\n"
            }
            else {
                newMessage += "\n"
                newMessage += msgConjunction.value!!
            }

            Log.d(MEOW,"finalMessageWithConjunction: $newMessage")

            val newMessage2 = msgTemplate2.value!!
            val posOfFirstAsterisk2 = newMessage2.indexOf("****")
            Log.d(MEOW,"posOfFirstAsterisk2: $posOfFirstAsterisk2")

            val stringPreAsterisk2 = newMessage2.removeRange(posOfFirstAsterisk2,newMessage2.length)
            Log.d(MEOW,"stringPreAsterisk2: $stringPreAsterisk2")

            val stringPostAsterisk2 = newMessage2.removeRange(0,posOfFirstAsterisk2 + 4)
            Log.d(MEOW,"stringPostAsterisk2: $stringPostAsterisk2")

            newMessage += stringPreAsterisk2 + msgWord2.value!! + stringPostAsterisk2
            Log.d(MEOW,"finalMessage: $newMessage")
        }
        return newMessage
    }



    // Testing






}