package com.example.eldenmessage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {

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
        )
    )

    fun getConstantWords(): MutableList<MutableList<String>> {
        return msgWords
    }
    fun getConstantTemplates(): MutableList<String> {
        return msgTemplates
    }
    fun getConstantConjunctions(): MutableList<String> {
        return msgConjunctions
    }


    var currentlySelecting: MutableLiveData<String> = MutableLiveData()

    var selectedWordCategory: MutableLiveData<Int> = MutableLiveData(0)
    val msgWord1: MutableLiveData<String> = MutableLiveData("")
    val msgWord2: MutableLiveData<String> = MutableLiveData("")
    fun getSelectedWord(getFirstWord: Boolean): String {
        return if (getFirstWord) {
            msgWord1.value!!
        }
        else {
            msgWord2.value!!
        }
    }
    fun getWordsByCategory(category: Int): MutableList<String> {
        selectedWordCategory.value = category
        return msgWords[category]
    }




    val msgTemplate1: MutableLiveData<String> = MutableLiveData("")
    val msgTemplate2: MutableLiveData<String> = MutableLiveData("")
    val msgConjunction: MutableLiveData<String> = MutableLiveData("")
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
    fun getSelectedTemplateOrConjunction(): String {
        return when (currentlySelecting.value) {
            "Template1" -> { msgTemplate1.value.toString() }
            "Template2" -> { msgTemplate2.value.toString() }
            "Conjunction" -> { msgConjunction.value.toString() }
            else -> { "" }
        }
    }


    var msgFormat: Boolean = false







}