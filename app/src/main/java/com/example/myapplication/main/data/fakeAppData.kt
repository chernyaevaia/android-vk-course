package com.example.myapplication.main.data

import com.example.myapplication.AppItem
import com.example.myapplication.R

object FakeAppData {
    val apps: List<AppItem> = listOf(
        AppItem(
            id = "sber_online",
            title = "СберБанк Онлайн — с Салютом",
            subtitle = "Больше чем банк",
            category = "Финансы",
            iconRes = R.drawable.outline_app_badging_24
        ),
        AppItem(
            id = "yandex_browser",
            title = "Яндекс.Браузер — с Алисой",
            subtitle = "Быстрый и безопасный браузер",
            category = "Инструменты",
            iconRes = R.drawable.outline_allergies_24
        ),
        AppItem(
            id = "mail_ru",
            title = "Почта Mail.ru",
            subtitle = "Почтовый клиент для любых ящиков",
            category = "Инструменты",
            iconRes = R.drawable.baseline_alternate_email_24
        ),
        AppItem(
            id = "yandex_navigator",
            title = "Яндекс Навигатор",
            subtitle = "Парковки и заправки — по пути",
            category = "Транспорт",
            iconRes = R.drawable.baseline_navigation_24
        ),
        AppItem(
            id = "my_mts",
            title = "Мой МТС",
            subtitle = "Мой МТС — центр экосистемы МТС",
            category = "Инструменты",
            iconRes = R.drawable.outline_4g_mobiledata_badge_24
        ),
        AppItem(
            id = "yandex_search",
            title = "Яндекс — с Алисой",
            subtitle = "Яндекс — поиск всегда под рукой",
            category = "Инструменты",
            iconRes = R.drawable.outline_alt_route_24
        )
    )

    fun findById(id: String): AppItem? = apps.firstOrNull { it.id == id }
}