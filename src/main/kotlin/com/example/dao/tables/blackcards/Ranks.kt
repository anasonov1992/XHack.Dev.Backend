package com.example.dao.tables.blackcards

import com.example.data.primitives.blackcards.RankType
import org.jetbrains.exposed.dao.id.IntIdTable

object Ranks: IntIdTable() {
    val type = enumeration<RankType>("type")
    val displayName = varchar("display_name", 64)
    val maxCardsInDeckCount = integer("max_cards_in_deck_count")
    val cardsUpMsgText = varchar("cards_up_msg_text", 256)
}