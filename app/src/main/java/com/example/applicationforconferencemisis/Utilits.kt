package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Data.Firebase.*
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.example.applicationforconferencemisis.Data.Models.User
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun makeToast(context: Context, arg: String) {
    Toast.makeText(context, arg, Toast.LENGTH_LONG).show()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    /* Функция расширения для Fragment, позволяет устанавливать фрагменты */
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(
            R.id.containerForFrag,
            fragment
        )?.commit()
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}

fun String.asDate(): String{
    val date = Date(this.toLong())
    val timeFormat = SimpleDateFormat("dd", Locale.getDefault())
    return timeFormat.format(date)
}

fun ImageView.downloadAndSetImage(url: String) {
    if (url == "") {
        val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
            .child("kisspng-refilmery-computer-icons-avatar-user-profile-avatar-vector-5ad7bb8f92b678_25771326152408769561009.png")
        path.downloadUrl.addOnCompleteListener {
            val photoUrl = it.result.toString()
            Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.ic_profile)
                .into(this)
        }
    } else {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_profile)
            .into(this)
    }
}

fun setMainSchedule() {
    val arrayConference3 = arrayListOf<MainSchedule>(
        MainSchedule("Registration for Workshops", "8:30-8:40"),
        MainSchedule("Workshops (see detailed schedule)", "8:40-9:25"),
        MainSchedule("Registration for Workshops", "9:25-9:35"),
        MainSchedule("Workshops (see detailed schedule)", "9:35-10:20"),
        MainSchedule("Break", "10:20-10:30"),
        MainSchedule(
            "Conference Opening: Svetlana G. Ter-Minasova " +
                    "Opening Plenary Talks " +
                    "Peter Watkins " +
                    "Reading Evolution: Helping Learners to Read Online " +
                    "Kelley Calvert " +
                    "Maintaining Motivation and Preventing Teacher Burnout with Student-сentered Instruction ",
            "10:30-12:30"
        ),
        MainSchedule("Lunch", "12:30-13:30"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "13:30-15:00"),
        MainSchedule("Break", "15:00-15:15"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "15:15-16:45"),
        MainSchedule("Break", "16:45-17:00"),
        MainSchedule(
            "Touchstone@MISIS English Language Program: 10th Anniversary Round-table",
            "17:00-18:00"
        ),
    )

    initFirebase()
    for (i in 0..arrayConference3.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule")
            .child(i.toString()).setValue(arrayConference3[i])
    }
    val arrayConference4 = arrayListOf<MainSchedule>(
        MainSchedule("Registration for Workshops", "8:30-8:40"),
        MainSchedule("Workshops (see detailed schedule)", "8:40-9:25"),
        MainSchedule("Registration for Workshops", "9:25-9:35"),
        MainSchedule("Workshops (see detailed schedule)", "9:35-10:20"),
        MainSchedule("Break", "10:20-10:30"),
        MainSchedule(
            "Plenary Tech Talks:\n" +
                    "Susana Anton (Cambridge University Press),\n" +
                    "Alexey Konobeev, Mikhail Sverdlov (Skyeng),\n" +
                    "Julia Khukalenko (Far Eastern Federal University),\n" +
                    "Maria Anikina (Uchi.ru)", "10:30-12:30"
        ),
        MainSchedule("Lunch", "12:30-13:30"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "13:30-15:00"),
        MainSchedule("Break", "15:00-15:15"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "15:15-16:45"),
        MainSchedule("Break", "16:45-17:00"),
        MainSchedule(
            "Young Voices:\n" +
                    "Q&A with David Crystal", "17:00-18:00"
        ),
    )

    for (i in 0..arrayConference4.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Schedule")
            .child(i.toString()).setValue(arrayConference4[i])
    }
    val arrayConference5 = arrayListOf<MainSchedule>(
        MainSchedule("Young Voices Section", "09:30-12:30"),
        MainSchedule(
            "Elena Solovova Honorary Readings (in Russian)\n" +
                    "\n" +
                    "Kuzmina L.G.\n" +
                    "E.N. Solovova and the Russian methodology of ELT\n" +
                    "Markova E.S.\n" +
                    "E.N. Solovova and the practice of ELT", "11:00-12:30"
        ),
        MainSchedule("Break", "12:30-13:00"),
        MainSchedule(
            "Closing Plenary Talk\n" +
                    "Maria V. Verbitskaya\n" +
                    "Prospective Model of the Russian National Examination in Foreign Languages (EGE) and the 21st Century Skills (in Russian, interpreting provided)",
            "13:00-14:00"
        ),
    )

    for (i in 0..arrayConference5.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 5").child("Schedule")
            .child(i.toString()).setValue(arrayConference5[i])
    }
}

fun setConferencesWorkshop() {
    val arrayConferencesWorkshop3 = arrayListOf<Conference>(
        Conference(
            "1",
            "Digital Methodology",
            "Classroom management: online vs offline.",
            "8:45 - 9:30",
            "Peresada Elena"
        ),
        Conference(
            "2",
            "New Frontiers of Testing and Assessment",
            "Energise, Optimise. Digitalise.",
            "8:45 - 9:30",
            "Kovaleva Yelena, Mogunova Elena, Petrova Natalia"
        ),
        Conference(
            "3",
            "Teacher Development",
            "Developing English speaking and writing skills of B1-C1 students by means of Instagram.",
            "8:45 - 9:30",
            "Kalintsev Alexey"
        ),
        Conference(
            "4",
            "Digital Methodology",
            "Running teacher training online.",
            "9:35-10:20",
            "Brooks Simon"
        ),
        Conference(
            "5",
            "Digital Methodology",
            "Teaching speaking and critical thinking skills in a low-tech online environment: approaches, tasks, online instruments.",
            "9:35-10:20",
            "Titova Svetlana "
        ),
        Conference(
            "6",
            "Teacher Development",
            "Collaborative design tools in learning.",
            "9:35-10:20",
            "Bauters Merja"
        ),
        Conference(
            "7",
            "Teacher Development",
            "Building Reflexes in a Foreign Language.",
            "9:35-10:20",
            "Marcq Antoine"
        ),
        Conference(
            "8",
            "Teacher Development",
            "It is while writing, that we are thinking most.",
            "9:35-10:20",
            "Ivanova Tatiana"
        ),
        Conference(
            "9",
            "Learner Diversity and Inclusion",
            "International career planning: preparing students for the global workplace.",
            "9:35-10:20",
            "Mitchell Peter"
        ),
        Conference(
            "10",
            "English Language Research",
            "Acquiring talent & improving performance through AI powered English testing.",
            "13:30-14:15",
            "Pandey Namita"
        ),
        Conference(
            "11",
            "Digital Methodology",
            "Teaching pronunciation digitally.",
            "14:15-15:00",
            "Gordyshevskaya Polina"
        ),
        Conference(
            "12",
            "English Language Research",
            "Innovation, collaboration, transformation: education for a connected world.",
            "15:00-15:45",
            "Cowin Jasmin"
        ),
        Conference(
            "13",
            "Teacher Development",
            "SFL genre pedagogy: teaching language for its function in the genres of writing.",
            "16:00-16:45",
            "Brisk Maria"
        ),
    )

    initFirebase()
    for (i in 0..arrayConferencesWorkshop3.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Workshop")
            .child(i.toString()).setValue(arrayConferencesWorkshop3[i])
    }

    val arrayConferenceWorkshop4 = arrayListOf<Conference>(
        Conference(
            "1",
            "Digital Methodology",
            "EFLtalks - teachers teaching teachers.",
            "8:45 - 9:30",
            "Howard Rob"
        ),
        Conference(
            "2",
            "Teacher Development",
            "Integrating critical thinking instruction into the Unified National Exam training programme.",
            "8:45 - 9:30",
            "Kapturova Evgenia"
        ),
        Conference(
            "3",
            "New Frontiers of Testing and Assessment",
            "Mind(full)ness: keep calm and learn.",
            "8:45 - 9:30",
            "Dilara Louise Hibbs"
        ),
        Conference(
            "4",
            "Teacher Development",
            "Mastering research competence & confidence: the chief editor’s view.",
            "8:45 - 9:30",
            "Tverdokchlebova Irina"
        ),
        Conference(
            "5",
            "Digital Methodology",
            "Project-based learning: digital tools worth adopting.",
            "8:45 - 9:30",
            "Aleshenko Olga, Ponidelko Lubov"
        ),
        Conference(
            "6",
            "New Frontiers of Testing and Assessment",
            "Less is more: using pictures to maximize student engagement and cut down class preparation time for Cambridge B2 First and C1 advanced.",
            "9:35-10:20",
            "Ribeiro Danilo"
        ),
        Conference(
            "7",
            "Digital Methodology",
            "An online simulated teaching intervention boosts preservice teacher self-efficacy and classroom readiness.",
            "9:35-10:20",
            "Klassen Robert"
        ),
        Conference(
            "8",
            "Teacher Development",
            "Pixton as a story-telling tool in ELT classroom.",
            "9:35-10:20",
            "Usoltseva Yulia, Wickrama Inosha"
        ),
        Conference(
            "9",
            "Digital Methodology",
            "Realia-based online lessons in the ELT.",
            "9:35-10:20",
            "Stepichev Petr"
        ),
        Conference(
            "10",
            "Learner Diversity and Inclusion",
            "Teaching simultaneous interpreting online: instruction and self-coaching.",
            "9:35-10:20",
            "Konurbaev Marklen"
        ),
        Conference(
            "11",
            "Teacher Development",
            "Language learner differences: seeing, testing, dealing.",
            "9:35-10:20",
            "Igolkina Natalia"
        ),
        Conference(
            "12",
            "English Language Research",
            "Intercultural awareness in the classroom.",
            "13:30 - 14:15",
            "Vasiliuskaite Giedre"
        ),
        Conference(
            "13",
            "Digital Methodology",
            "Incorporating digital social media research into qualitative research methodologies.",
            "14:15 - 15:00",
            "Christiansen Martha"
        ),
        Conference(
            "14",
            "English Language Research",
            "What does it mean to be polite?",
            "15:00 - 15:45",
            "Golechkova Tatiana"
        ),
        Conference(
            "15",
            "Teacher Development",
            "Rising to the digital challenge: introducing LanguageCert’s latest online assessment solutions.",
            "16:00 - 16:45",
            "Golding Janet"
        ),
        Conference(
            "16",
            "Digital Methodology",
            "Better online outcomes.",
            "16:00 - 16:45",
            "Dellar Hugh"
        ),
    )
    for (i in 0..arrayConferenceWorkshop4.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Workshop")
            .child(i.toString()).setValue(arrayConferenceWorkshop4[i])
    }
}

fun setConferencesSessions() {
    val arrayConferenceSession3 = arrayListOf<Conference>(
        Conference(
            "1",
            "English Language Research",
            "Dictionary use in digital era (with special reference to English learners' dictionaries).",
            "13.30-13.45",
            "Karpova Olga"
        ),
        Conference(
            "2",
            "English Language Research",
            "Relevance of EMI for Higher Education in Russian universities.",
            "13.30-13.45",
            "Bulina Evgeniya, Burenina Natalya, Gvozdeva Marina, Pan'ko Varvara, Zvezdova Aleksandra"
        ),
        Conference(
            "3",
            "Intercultural Communication",
            "A country's tourism slogan as a marker of national identity.",
            "13.30-13.45",
            "Shilova Ekaterina"
        ),
        Conference(
            "4",
            "English Language Research",
            "Achromatic colours in the image of the city in Russian and English-language imaginative prose.",
            "13.45-14.00",
            "Barabushka Irina"
        ),
        Conference(
            "5",
            "English Language Research",
            "Content and language integrated learning for university students.",
            "13.45-14.00",
            "Melekhina Elena"
        ),
        Conference(
            "6",
            "Intercultural Communication",
            "An international project \"Daily meals in Russia and in the USA as they are viewed by Russian elementary schoolchildren\".",
            "13.45-14.00",
            "Kravchenko Natalya"
        ),
        Conference(
            "7",
            "English Language Research",
            "Contrastive analysis of the names of lovers in English and Russian languages.",
            "14.00-14.15",
            "Fedosova Valentina"
        ),
        Conference(
            "8",
            "English Language Research",
            "A look into learning environment at Touchstone@MISIS language programme: 2015-2020.",
            "14.00-14.15",
            "Ermakova Polina"
        ),
        Conference(
            "9",
            "Intercultural Communication",
            "TOPIC TBC",
            "14.00-14.15",
            "Kirpicheva Darya"
        ),
        Conference(
            "10",
            "English Language Research",
            "Challenges for ESP medical classroom.",
            "14.15-14.30",
            "Rodicheva Anna, Zaitseva Tatiana"
        ),
        Conference(
            "11",
            "English Language Research",
            "The translation competition of contemporary British prose in Perm region.",
            "14.15-14.30",
            "Gritsenko Elena, Polyakova Svetlana"
        ),
        Conference("12", "Intercultural Communication", "TOPIC TBC", "14.15-14.30", "Rodko Olga"),
        Conference(
            "13",
            "English Language Research",
            "Determination of the optimal translated correspondence.",
            "14.30-14.45",
            "Lukina Lyudmila"
        ),
        Conference(
            "14",
            "English Language Research",
            "Network community for the transport university students training for professional communication (a case study for exchange programme \"International Logistics\" (Ural State University for Railway Transport, Russia - Wildau Technical University, Germany).",
            "14.30-14.45",
            "Panchenkova Maria"
        ),
        Conference("15", "English Language Research", "TOPIC TBC", "14.45-15.00", "Erken Emily"),
        Conference(
            "16",
            "English Language Research",
            "To the problem of revealing national peculiarity of semantics.",
            "14.45-15.00",
            "Sternina Marina"
        ),
        Conference(
            "17",
            "Teacher Development",
            "English in a multilingual world: implications for teachers.",
            "15.15-15.30",
            "De Jong Ester"
        ),
        Conference(
            "18",
            "English Language Research",
            "Methodological issues in digital multimodal literacy research.",
            "15.15-15.30",
            "Yi Youngjoo"
        ),
        Conference(
            "19",
            "Digital Methodology",
            "Expanding meaning-making possibilities through multimodal composition.",
            "15.15-15.30",
            "Smith Blaine"
        ),
        Conference(
            "20",
            "Teacher Development",
            "Professional self-development of teachers in online environment.",
            "15.30-15.45",
            "Avdeeva Yulia, Grashchenkova Galina"
        ),
        Conference(
            "21",
            "English Language Research",
            "Information literacy for classroom research.",
            "15.30-15.45",
            "Safonkina Olga"
        ),
        Conference(
            "22",
            "Digital Methodology",
            "Blended Learning via Distance Learning: New Approaches in Teaching English.",
            "15.30-15.45",
            "Igonina Galina"
        ),
        Conference(
            "23",
            "Teacher Development",
            "Designing the course \"ICT in Education\" for beginning ESL teachers at Astrakhan State University.",
            "15.45-16.00",
            "Galichkina Elena"
        ),
        Conference(
            "24",
            "English Language Research",
            "CLIL-approach as extra motivation for students aged 10-12.",
            "15.45-16.00",
            "Yepova Olga"
        ),
        Conference(
            "25",
            "Digital Methodology",
            "Hybrid environment: managing a class.",
            "15.45-16.00",
            "Ponidelko Lyubov, Petrova Natalya"
        ),
        Conference(
            "26",
            "Teacher Development",
            "In-service training for ELT: project-based learning with authentic mass media.",
            "16.00-16.15",
            "Dvorghets Olga"
        ),
        Conference(
            "27",
            "English Language Research",
            "Mind mapping and its implementation into educational process.",
            "16.00-16.15",
            "Krivenko Lyudmila"
        ),
        Conference(
            "28",
            "Digital Methodology",
            "The peculiarities of teaching ESP online only.",
            "16.00-16.15",
            "Klochko Konstantin"
        ),
        Conference(
            "29",
            "Teacher Development",
            "Adapting teachers to changes in the curriculum.",
            "16.15-16.30",
            "Kiryukina Natalya"
        ),
        Conference(
            "30",
            "English Language Research",
            "Linguodidactic features of teaching English at the primary level.",
            "16.15-16.30",
            "Skachkova Sofia"
        ),
        Conference(
            "31",
            "Digital Methodology",
            "Establishing productive learning environment under any technical circumstances.",
            "16.15-16.30",
            "Kovaleva Yelena"
        ),
        Conference(
            "32",
            "English Language Research",
            "TOPIC TBC",
            "16.30-16.45",
            "Kolesnikova Marina"
        ),
        Conference(
            "33",
            "Digital Methodology",
            "Mobile learning and foreign language teaching. How do they align?",
            "16.30-16.45",
            "Golova Ekaterina"
        )
    )

    initFirebase()
    for (i in 0..arrayConferenceSession3.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Session")
            .child(i.toString()).setValue(arrayConferenceSession3[i])
    }

    val arrayConferenceSession4 = arrayListOf<Conference>(
        Conference(
            "1",
            "Digital Methodology",
            "Looking to the new normal: Qualitative methodologies for a post-pandemic era.",
            "13.30-13.45",
            "Christiansen M. Sidury"
        ),
        Conference(
            "2",
            "New Frontiers of Testing and Assessment",
            "National Discourse Pattern of Texts in Teaching FL.",
            "13.30-13.45",
            "Korovina Irina"
        ),
        Conference(
            "3",
            "Teacher Development",
            "From shaken and stirred to blended and flipped – the future of ELT?",
            "13.30-13.45",
            "Evans Edward"
        ),
        Conference(
            "4",
            "Digital Methodology",
            "Developing digital literacy in the process of language learning.",
            "13.45-14.00",
            "Kravets Olga"
        ),
        Conference(
            "5",
            "New Frontiers of Testing and Assessment",
            "Internet-based exam: advantages, disadvantages, possible prospects for use.",
            "13.45-14.00",
            "Kharlamenko Inna"
        ),
        Conference(
            "6",
            "Teacher Development",
            "Teaching gifted and talented learners.",
            "13.45-14.00",
            "Novikova Vera"
        ),
        Conference(
            "7",
            "Digital Methodology",
            "Universal collaborative ESP/ESL teaching methods in digital education: first approach.",
            "14.00-14.15",
            "Vasilyeva Irina"
        ),
        Conference(
            "8",
            "New Frontiers of Testing and Assessment",
            "Assessment as a digital learning tool.",
            "14.00-14.15",
            "Vladyko Anzhelika"
        ),
        Conference(
            "9",
            "Teacher Development",
            "Student-friendly approach to language classes for engineering students: example of NUST MISiS English programme.",
            "14.00-14.15",
            "Knyazeva Olga, Polukhina Irina"
        ),
        Conference(
            "10",
            "Digital Methodology",
            "Creating Student-Centred Project-Based Courses in English using Moodle.",
            "14.15-14.30",
            "Kuznetsova Elena"
        ),
        Conference(
            "11",
            "New Frontiers of Testing and Assessment",
            "Factors contributing to the final IELTS exam in the volatile university environment.",
            "14.15-14.30",
            "Rossikhina Olga, Chernushkina Nina"
        ),
        Conference(
            "12",
            "Teacher Development",
            "How to plan and organize your online class.",
            "14.15-14.30",
            "Vostrikova Irina"
        ),
        Conference(
            "13",
            "Digital Methodology",
            "Language teaching in a (post) pandemic world.",
            "14.30-14.45",
            "Mogunova Elena"
        ),
        Conference(
            "14",
            "New Frontiers of Testing and Assessment",
            "Language assessment essentials for future teachers.",
            "14.30-14.45",
            "Gorizontova Anna"
        ),
        Conference(
            "15",
            "Teacher Development",
            "Touchstone@MISIS Culture and Programme manual.",
            "14.30-14.45",
            "Isaeva Anna, Esipov Roman"
        ),
        Conference(
            "16",
            "Digital Methodology",
            "Methodological potential of using integrated multimedia tasks in teaching professional English to non-language students.",
            "14.45-15.00",
            "Lavrentyeva Natalya"
        ),
        Conference(
            "17",
            "Digital Methodology",
            "Narrative modeling with StoryQ: integrating language arts, mathematics, and computing to create pathways to artificial intelligence careers.",
            "15.15-15.30",
            "Jiang Shiyan"
        ),
        Conference(
            "18",
            "Learner Diversity and Inclusion",
            "Longread as an efficient tool for developing learner autonomy through creative mindset in digital space.",
            "15.15-15.30",
            "Belkina Oksana, Lazorak Olga, Yaroslavova Elena"
        ),
        Conference(
            "19",
            "Teacher Development",
            "Language, perception, and TEFL: implications for teacher development.",
            "15.15-15.30",
            "Kravchenko Aleksander"
        ),
        Conference(
            "20",
            "Digital Methodology",
            "Teaching foreign languages online: a mixed blessing?",
            "15.30-15.45",
            "Yankova Diana"
        ),
        Conference(
            "21",
            "Learner Diversity and Inclusion",
            "TOPIC TBC",
            "15.30-15.45",
            "Gorelova Natalie"
        ),
        Conference(
            "22",
            "Teacher Development",
            "Learning L2 grammar from original version TV series.",
            "15.30-15.45",
            "Pattemore-Plotnikova Anastasia"
        ),
        Conference(
            "23",
            "Digital Methodology",
            "Digital tools for Academic Writing Course design.",
            "15.45-16.00",
            "Tikhomirova Elizaveta"
        ),
        Conference(
            "24",
            "Learner Diversity and Inclusion",
            "Assistive technology in teaching EFL to children with hearing impairments.",
            "15.45-16.00",
            "Cheprasova Tatyana"
        ),
        Conference(
            "25",
            "Teacher Development",
            "Key vectors of educational interactivity implementation in English classroom.",
            "15.45-16.00",
            "Bogatyrev Andrei"
        ),
        Conference(
            "26",
            "Digital Methodology",
            "Integration of the digital storytelling into the ELT classroom.",
            "16.00-16.15",
            "Arkhipova Tatiana"
        ),
        Conference(
            "27",
            "Digital Methodology",
            "Adapting Dictogloss to digital transformation.",
            "16.15-16.30",
            "Teplyakova Anastasia"
        ),
        Conference(
            "28",
            "Digital Methodology",
            "A Case Study: Interdisciplinary Project Work in ELT Using Python At NUST \"MISiS\"",
            "16.30-16.45",
            "Komarova Maria"
        ),
    )

    for (i in 0..arrayConferenceSession4.size - 1) {
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Session")
            .child(i.toString()).setValue(arrayConferenceSession4[i])
    }
}

//Yekaterina-Morozova , Yelena-Kovaleva
fun addUsers() {
    val users = arrayOf(
        User(
            "povarenkinaia@mail.ru",
            "Irina Povarenkina",
            "Irina-Povarenkina",
            "nate20Irina21"
        ),
        User(
            "cheprasovatatjana@rambler.ru",
            "Tatyana Cheprasova",
            "Tatyana-Cheprasova",
            "nate20Tatyana21"
        ),
        User(
            "lizareutova@gmail.com",
            "Elizaveta Reutova",
            "Elizaveta-Reutova",
            "nate20Elizaveta21"
        ),
        User(
            "merilu933@mail.ru",
            "Tatiana Galkina",
            "Tatiana-Galkina",
            "nate20Tatiana21"
        ),
        User(
            "kuv@list.ru",
            "Olga Kuvshinova",
            "Olga-Kuvshinova",
            "nate20Olga21"
        ),
        User(
            "olga.m.karpova@gmail.com",
            "Karpova OLga",
            "Olga-Karpova",
            "nate20Olga21"
        ),
        User(
            "antoine@resource-education.com",
            "Antoine Marcq",
            "Antoine-Marcq",
            "nate20Antoine21"
        ),
        User(
            "theasia@yahoo.com",
            "Anastasia Teplyakova",
            "Anastasia-Teplyakova",
            "nate20Anastasia21"
        ),
        User(
            "Korolyeva@mail.ru",
            "Korolyeva Tatyana Alexandrovna",
            "Tatyana-Korolyeva",
            "nate20Tatyana21"
        ),
        User(
            "gala_igonina@mail.ru",
            "Galina Igonina",
            "Galina-Igonina",
            "nate20Galina21"
        ),
        User(
            "westernvologda@gmail.com",
            "Olga Kosheleva",
            "Olga-Kosheleva",
            "nate20Olga21"
        ),
        User(
            "korirfox@gmail.com",
            "Irina Korovina",
            "Irina-Korovina",
            "nate20Irina21"
        ),
        User(
            "okuznetchik@yandex.ru",
            "Olga Safonkina",
            "Olga-Safonkina",
            "nate20Olga21"
        ),
        User(
            "kati30_82@mail.ru",
            "Ekaterina Andreyevna Danilova",
            "Ekaterina-Danilova",
            "nate20Ekaterina21"
        ),
        User(
            "49c942@mail.ru",
            "Natalya Kravchenko",
            "Natalya-Kravchenko",
            "nate20Natalya21"
        ),
        User(
            "andy1512@yandex.ru",
            "Andrei L. Chernyshev",
            "Andrei-Chernyshev",
            "nate20Andrei21"
        ),
        User(
            "lina.gordyshevskaya@gmail.com",
            "Polina Gordyshevskaya",
            "Polina-Gordyshevskaya",
            "nate20Polina21"
        ),
        User(
            "sckachckova.sofi@gmail.com",
            "Sofia Skachkova",
            "Sofia-Skachkova",
            "nate20Sofia21"
        ),
        User(
            "lisi4ka1602@yandex.ru",
            "Marina Kolesnikova",
            "Marina-Kolesnikova",
            "nate20Marina21"
        ),
        User(
            "idiomal@yandex.ru",
            "Elena Selifonova",
            "Elena-Selifonova",
            "nate20Elena21"
        ),
        //20
        User(
            "olgakravets@list.ru",
            "Olga Kravets",
            "Olga-Kravets",
            "nate20Olga21"
        ),
        User(
            "elena.galichkina@mail.ru",
            "Elena Galichkina",
            "Elena-Galichkina",
            "nate20Elena21"
        ),
        User(
            "ekaterinakolesnikova@rambler.ru",
            "Yekaterina Morozova",
            "Yekaterina-Morozova",
            "nate20Yekaterina21"
        ),
        User(
            "ribeiro.danilo1993@gmail.com",
            "Danilo Ribeiro",
            "Danilo-Ribeiro",
            "nate20Danilo21",
        ),
        User(
            "maria_gallyamova@mail.ru",
            "Maria Gallyamova",
            "Maria-Gallyamova",
            "nate20Maria21"
        ),
        User(
            "Teacherso@macaw.me",
            "Soraya Ieda Gubich",
            "Soraya-Ieda Gubich",
            "nate20Soraya21"
        ),
        User(
            "boltneva@mail.ru",
            "Olga Yurievna Boltneva",
            "Olga-Boltneva",
            "nate20Olga21"
        ),
        User(
            "polsvetlana@yandex.ru",
            "Svetlana Polyakova",
            "Svetlana-Polyakova",
            "nate20Svetlana21"
        ),
        User(
            "gritsenko@inbox.ru",
            "Elena Gritsenko",
            "Elena-Gritsenko",
            "nate20Elena21"
        ),
        User(
            "barabashka84@mail.ru",
            "Irina Barabushka",
            "Irina-Barabushka",
            "nate20Irina21"
        ),
        User(
            "tklikushina@yandex.ru",
            "Klikushina Tatiana Georgievna",
            "Tatiana-Klikushina",
            "nate20Tatiana21"
        ),
        User(
            "anastasia.plotnikova@ub.edu",
            "Anastasia Pattemore-Plotnikova",
            "Anastasia-Pattemore-Plotnikova",
            "nate20Anastasia21"
        ),
        User(
            "osdvor@rambler.ru",
            "Olga Solomonovna Dvorghets",
            "Olga-Solomonovna",
            "nate20Olga21"
        ),
        User(
            "inaviri@gmail.com",
            "Irina I. Vasilyeva",
            "Irina-Vasilyeva",
            "nate20Irina21"
        ),
        User(
            "ats584793@mail.ru",
            "Arkhipova Tatiana",
            "Tatiana-Arkhipova",
            "nate20Tatiana21"
        ),
        User(
            "nataigolkina@mail.ru",
            "Natalia Igolkina",
            "Natalia-Igolkina",
            "nate20Natalia21"
        ),
        User(
            "valenttif@gmail.com",
            "Valentina Igorevna Fedosova",
            "Valentina-Fedosova",
            "nate20Valentina21"
        ),
        User(
            "englishlearners2@gmail.com",
            "Mariia Kuznetcova",
            "Mariia-Kuznetcova",
            "nate20Mariia21"
        ),
        User(
            "zmeykasofi@mail.ru",
            "Sophia Polyankina",
            "Sophia-Polyankina",
            "nate20Sophia21"
        ),
        User(
            "margarita.patkina@gmail.com",
            "Margarita Patkina",
            "Margarita-Patkina",
            "nate20Margarita21"
        ),
        //40
        User(
            "Larionova.misis@mail.ru",
            "Elena Leonidovna Larionova",
            "Elena-Larionova",
            "nate20Elena21"
        ),
        User(
            "Danasmb@hotmail.com",
            "Dana Saulembekova",
            "Dana-Saulembekova",
            "nate20Dana21"
        ),
        User(
            "a.shchekochikhina@gmail.com",
            "Shchekochikhina Anastasia Vadimovna",
            "Anastasia-Shchekochikhina",
            "nate20Anastasia21"
        ),
        User(
            "evgeniya.kuznetsova90@gmail.com",
            "Evgeniya Kuznetsova",
            "Evgeniya-Kuznetsova",
            "nate20Evgeniya21"
        ),
        User(
            "artameli@mail.ru",
            "Bagdasarova Ilona",
            "Ilona-Bagdasarova",
            "nate20Ilona21"
        ),
        User(
            "anastasiakhodakova@gmail.com",
            "Anastasia Khodakova",
            "Anastasia-Khodakova",
            "nate20Anastasia21"
        ),
        User(
            "tonya.ks@mail.ru",
            "Kolesnikova Antonina Andreevna",
            "Antonina-Kolesnikova",
            "nate20Antonina21"
        ),
        User(
            "e.tikhomirova@skoltech.ru",
            "Elizaveta Tikhomirova",
            "Elizaveta-Tikhomirova",
            "nate20Elizaveta21"
        ),
        User(
            "ujushka@mail.ru",
            "Uliana Ivanova",
            "Uliana-Ivanova",
            "nate20Uliana21"
        ),
        User(
            "olyarai@yandex.ru",
            "Olga Raylyan",
            "Olga-Raylyan",
            "nate20Olga21"
        ),
        User(
            "elkar09@yandex.ru",
            "Elvira Kartseva",
            "Elvira-Kartseva",
            "nate20Elvira21"
        ),
        User(
            "ekaterina-golova@list.ru",
            "Ekaterina A.Golova",
            "Ekaterina-Golova",
            "nate20Ekaterina21"
        ),
        User(
            "konstklochko@gmail.com",
            "Konstantin A. Klochko",
            "Konstantin-Klochko",
            "nate20Konstantin21"
        ),
        User(
            "gorelovachernyh@mail.ru",
            "Natalie Gorelova",
            "Natalie-Gorelova",
            "nate20Natalie21"
        ),
        User(
            "yi.57@osu.edu",
            "Youngjoo Yi",
            "Youngjoo-Yi",
            "nate20Youngjoo21",
            "Speaker"
        ),
        User(
            "lyudmilaab@mail.ru",
            "Krivenko Lyudmila Aleksandrovna",
            "Lyudmila-Krivenko",
            "nate20Lyudmila21"
        ),
        User(
            "polina.ermakova.misis@gmail.com",
            "Polina Ermakova",
            "Polina-Ermakova",
            "nate20Polina21"
        ),
        User(
            "lkaz11996@mail.ru",
            "Kazantseva Luidmila",
            "Luidmila-Kazantseva",
            "nate20Luidmila21"
        ),
        User(
            "roza_rafagatovna@mail.ru",
            "Razina Mudarisova",
            "Razina-Mudarisova",
            "nate20Razina21"
        ),
        User(
            "andrewwin@yandex.ru",
            "Natalja Shustina",
            "Natalja-Shustina",
            "nate20Natalja21"
        ),
        //60
        User(
            "marthasidury.christiansen@utsa.edu",
            "M. Sidury Christiansen",
            "Sidury-Christiansen",
            "nate20Sidury21"
        ),
        User(
            "grigoryeva.ekaterina@mail.ru",
            "Ekaterina Grigoryeva",
            "Ekaterina-Grigoryeva",
            "nate20Ekaterina21"
        ),
        User(
            "deva_elizovo@mail.ru",
            "Olga Ershova",
            "Olga-Ershova",
            "nate20Olga21"
        ),
        User(
            "niya@mail.ru",
            "Tolstykh Olesya",
            "Olesya-Tolstykh",
            "nate20Olesya21"
        ),
        User(
            "aquastel@mail.ru",
            "Ilchenko Elena Vladimirovna",
            "Elena-Ilchenko",
            "nate20Elena21"
        ),
        User(
            "5765744@mail.ru",
            "Anna Krupchenko",
            "Anna-Krupchenko",
            "nate20Anna21"
        ),
        User(
            "tomabelyaeva6@yandex.ru",
            "Tamara Belyaeva",
            "Tamara-Belyaeva",
            "nate20Tamara21"
        ),
        User(
            "mariaurahara@mail.ru",
            "Semenova Maria",
            "Maria-Semenova",
            "nate20Maria21"
        ),
        User(
            "ninellru@yandex.ru",
            "Nina Chrenushkina",
            "Nina-Chrenushkina",
            "nate20Nina21"
        ),
        User(
            "annaalex79@mail.ru",
            "Alekseeva Anna",
            "Anna-Alekseeva",
            "nate20Anna21"
        ),
        User(
            "ollie.nency1996@gmail.com",
            "Olga Knyazeva",
            "Olga-Knyazeva",
            "nate20Olga21",
        ),
        User(
            "galina-the-happy@mail.ru",
            "Galina Grashchenkova",
            "Galina-Grashchenkova",
            "nate20Galina21"
        ),
        User(
            "irysya.polukhina@gmail.com",
            "Irina Polukhina",
            "Irina-Polukhina",
            "nate20Irina21"
        ),
        User(
            "Irene16@yandex.ru",
            "Irina Vladimirovna Ignatova",
            "Irina-Ignatova",
            "nate20Irina21"
        ),
        User(
            "ab.galina@gmail.com",
            "Galina Abramova",
            "Galina-Abramova",
            "nate20Galina21"
        ),
        User(
            "olga.connolly@yandex.ru",
            "Olga Connolly",
            "Olga-Connolly",
            "nate20Olga21"
        ),
        User(
            "tatiana-luck@mail.ru",
            "Tatiana Vasileva",
            "Tatiana-Vasileva",
            "nate20Tatiana21"
        ),
        User(
            "natali2670@mail.ru",
            "Shchitova Natalya Georgievna",
            "Natalya-Shchitova",
            "nate20Natalya21"
        ),
        User(
            "sandra77779@mail.ru",
            "Alexandra Shafeeva",
            "Alexandra-Shafeeva",
            "nate20Alexandra21"
        ),
        User(
            "prichesnyaeva@gmail.com",
            "Evgeniya Prichesnyaeva",
            "Evgeniya-Prichesnyaeva",
            "nate20Evgeniya21"
        ),
        //80
        User(
            "melnikova.ea@misis.ru",
            "Melnikova Evgeniia Anatolyevna",
            "Evgeniia-Melnikova",
            "nate20Evgeniia21"
        ),
        User(
            "luda_konstant@mail.ru",
            "Lyudmila Nacharova",
            "Lyudmila-Nacharova",
            "nate20Lyudmila21"
        ),
        User(
            "m.berezina@mail.ru",
            "Maria V. Molchanova",
            "Maria-Molchanova",
            "nate20Maria21"
        ),
        User(
            "Juliaseaa@gmail.com",
            "Yulia Duriagina",
            "Yulia-Duriagina",
            "nate20Yulia21"
        ),
        User(
            "zulya5596@gmail.com",
            "Zulfiya Abdullayeva",
            "Zulfiya-Abdullayeva",
            "nate20Zulfiya21"
        ),
        User(
            "nbochegova1@yandex.ru",
            "Natalya Bochegova",
            "Natalya-Bochegova",
            "nate20Natalya21"
        ),
        User(
            "basilsbox2@gmail.com",
            "Makukha Vasiliy",
            "Vasiliy-Makukha",
            "nate20Vasiliy21"
        ),
        User(
            "shishknat@mail.ru",
            "Shishkina Natalia Mikhailovna",
            "Natalia-Shishkina",
            "nate20Natalia21"
        ),
        User(
            "anna.gorizontova@peoplecert.org",
            "Anna Gorizontova",
            "Anna-Gorizontova",
            "nate20Anna21"
        ),
        User(
            "olegivchenko11@mail.ru",
            "Oleg Ivchenko",
            "Oleg-Ivchenko",
            "nate20Oleg21"
        ),
        User(
            "potemkinatv@mail.ru",
            "Potemkina Tatiana",
            "Tatiana-Potemkina",
            "nate20Tatiana21"
        ),
        User(
            "teacherstepichev@narod.ru",
            "Petr A. Stepichev",
            "Petr-Stepichev",
            "nate20Petr21"
        ),
        User(
            "lalaith@mail.ru",
            "Liubov Ponidelko",
            "Liubov-Ponidelko",
            "nate20Liubov21"
        ),
        User(
            "natalie_ye@mail.ru",
            "Petrova Natalya",
            "Natalya-Petrova",
            "nate20Natalya21"
        ),
        User(
            "veranovik@mail.ru",
            "Novikova Vera Pavlovna",
            "Vera-Novikova",
            "nate20Vera21"
        ),
        User(
            "annrybakova@gmail.com",
            "Anna Rybakova",
            "Anna-Rybakova",
            "nate20Anna21"
        ),
        User(
            "Hrox@mail.ru",
            "Oxana Hrushcheva",
            "Oxana-Hrushcheva",
            "nate20Oxana21"
        ),
        User(
            "iptverdokhlebova@gmail.com",
            "Ирина Твердохлебова",
            "Irina-Tverdokchlebova",
            "nate20Irina21",
            "Speaker"
        ),
        User(
            "Yelena.v.kovaleva@gmail.com",
            "Yelena Kovaleva",
            "Yelena-Kovaleva1",
            "nate20Yelena21"
        ),
        User(
            "lena_leto_89@mail.ru",
            "Elena Yurievna Marchenko",
            "Elena-Marchenko",
            "nate20Elena21"
        ),
        User(
            "lookyna@gmail.com",
            "Lyudmila Vladimirovna Lukina",
            "Lyudmila-Lukina",
            "nate20Lyudmila21"
        ),
        User(
            "oyepova@mail.ru",
            "Olga Yepova",
            "Olga-Yepova",
            "nate20Olga21"
        ),
        User(
            "ivostrikova@mail.ru",
            "Irina Vostrikova",
            "Irina-Vostrikova",
            "nate20Irina21"
        ),
        User(
            "dyankova@nbu.bg",
            "Diana Yankova",
            "Diana-Yankova",
            "nate20Diana21"
        ),
        User(
            "nataliakiryukhina@yandex.ru",
            "NATALIA KIRYUKHINA",
            "NATALIA-KIRYUKHINA",
            "nate20NATALIA21"
        ),
        User(
            "sternina@vmail.ru",
            "Marina Sternina",
            "Marina-Sternina",
            "nate20Marina21"
        ),
        User(
            "shilova_ivsu@mail.ru",
            "Ekaterina Shilova",
            "Ekaterina-Shilova",
            "nate20Ekaterina21"
        ),
        User(
            "tatianazaitseva2013@yandex.ru",
            "Tatiana A. Zaitseva",
            "Tatiana-Zaitseva",
            "nate20Tatiana21"
        ),
        User(
            "ikharlamenko@yandex.ru",
            "Kharlamenko Inna Vladimirovna",
            "Inna-Kharlamenko",
            "nate20Inna21"
        ),
        User(
            "janet.golding@languagecert.org",
            "Janet Golding",
            "Janet-Golding",
            "nate20Janet21"
        ),
        User(
            "ekuzsocial@gmail.com",
            "Elena Kuznetsova",
            "Elena-Kuznetsova",
            "nate20Elena21"
        ),
        User(
            "umk_kizt@mail.ru",
            "Panchenkova Mariia",
            "Mariia-Panchenkova",
            "nate20Mariia21"
        ),
        User(
            "Overanaesthetic@gmail.com",
            "Yelena Kovaleva",
            "Yelena-Kovaleva",
            "nate20Yelena21"
        ),
        User(
            "aleksena87@yandex.ru",
            "Elena Mogunova",
            "Elena-Mogunova",
            "nate20Elena21"
        ),
        User(
            "anzhelika.vladyko@gmail.com",
            "Anzhelika Vladyko",
            "Anzhelika-Vladyko",
            "nate20Anzhelika21"
        ),
        User(
            "irenevl@yandex.ru",
            "Irina Shchukina",
            "Irina-Shchukina",
            "nate20Irina21"
        ),
        User(
            "tatiana-szelinger@yandex.ru",
            "Tatiana Ivanova",
            "Tatiana-Ivanova",
            "nate20Tatiana21"
        ),
        User(
            "anais8512@gmail.com",
            "Anna Isaeva",
            "Anna-Isaeva",
            "nate20Anna21"
        ),
        User(
            "lidia.agafonova@gmail.com",
            "Lidia Agafonova",
            "Lidia-Agafonova",
            "nate20Lidia21"
        ),
        User(
            "kaif0703@mail.ru",
            "Каюмова Ирина Фуатовна",
            "Irina-Kayumova",
            "nate20Irina21"
        ),
        //20
        User(
            "belkinaov@susu.ru",
            "Oksana Belkina",
            "Oksana-Belkina",
            "nate20Oksana21"
        ),
        User(
            "lazorakov@susu.ru",
            "Olga Lazorak",
            "Olga-Lazorak",
            "nate20Olga21"
        ),
        User(
            "PushkinaIV@mgpu.ru",
            "Irina Pushinina",
            "Irina-Pushinina",
            "nate20Irina21"
        ),
        User(
            "sssmak2@mail.ru",
            "Svetlana Makeeva",
            "Svetlana-Makeeva",
            "nate20Svetlana21"
        ),
        User(
            "kinisha2@yandex.ru",
            "Yulia",
            "Yulia-Semenova",
            "nate20Yulia21"
        ),
        User(
            "Vvergen@gmail.com",
            "Vera Vvedenskaya",
            "Vera-Vvedenskaya",
            "nate20Vera21"
        ),
        User(
            "AimaletdinovRT@mgpu.ru",
            "Renat Aymaletdinov",
            "Renat-Aymaletdinov",
            "nate20Renat21"
        ),
        User(
            "pashkovskaya21@yandex.ru",
            "Olga Arutyunyan",
            "Olga-Arutyunyan",
            "nate20Olga21"
        ),
        User(
            "kate.vishnevsk@ya.ru",
            "Ekaterina Vishnevskaya",
            "Ekaterina-Vishnevskaya",
            "nate20Ekaterina21"
        ),
        User(
            "DolgonovskajaLJ@mgpu.ru",
            "Dolgonovskaya Liliya",
            "Liliya-Dolgonovskaya",
            "nate20Liliya21"
        ),
        //10
        User(
            "ol.baryschnikova@yandex.ru",
            "Olga Baryshnikova",
            "Olga-Baryshnikova",
            "nate20Olga21"
        ),
        User(
            "a-chistik@yandex.ru",
            "Mrs. Anna A. Chistik",
            "Anna-Chistik",
            "nate20Anna21"
        ),
        User(
            "al.kalintsev@gmail.com",
            "Aleksey Kalintsev",
            "Aleksey-Kalintsev",
            "nate20Aleksey21"
        ),
        User(
            "I.malova579@gmail.com",
            "Irina Malova",
            "Irina-Malova",
            "nate20Irina21"
        ),
        User(
            "es.markova@mail.ru",
            "Markova Elena",
            "Elena-Markova",
            "nate20Elena21"
        ),
        User(
            "lushagina@mail.ru",
            "Lushagina Anna",
            "Anna-Lushagina",
            "nate20Anna21"
        ),
        User(
            "salir@mail.ru",
            "Irina Salamatina",
            "Irina-Salamatina",
            "nate20Irina21"
        ),
        User(
            "lautre88@mail.ru",
            "Natalia Sergeevna Kiseleva",
            "Natalia-Kiseleva",
            "nate20Natalia21"
        ),
        User(
            "teacherolga@mail.ru",
            "Krylova Olga Valerievna",
            "Olga-Krylova",
            "nate20Olga21"
        ),
        User(
            "zubchik69@mail.ru",
            "Olga Malianova",
            "Olga-Malianova",
            "nate20Olga21"
        ),
        //10
        User(
            "obelova121@gmail.com",
            "Olga Belova",
            "Olga-Belova",
            "nate20Olga21"
        ),
        User(
            "kochubeeva1301@gmail.com",
            "Kochubeeva Anastasiia",
            "Anastasiia-Kochubeeva",
            "nate20Anastasiia21"
        ),
        User(
            "n-pripolzina@mail.ru",
            "Natalie Pripolzina",
            "Natalie-Pripolzina",
            "nate20Natalie21"
        ),
        User(
            "lukanina.mv@misis.ru",
            "Lukanina Maria",
            "Maria-Lukanina",
            "nate20Maria21"
        ),
        User(
            "cdp@digwheel.com",
            "Dmitry Chinenov",
            "Dmitry-Chinenov",
            "nate20Dmitry21"
        ),
        User(
            "PetrovaEB@yandex.ru",
            "Elena Petrova",
            "Elena-Petrova",
            "nate20Elena21"
        ),
        User(
            "elizaveta.shtukaturova@gmail.com",
            "Elizaveta Shtukaturova",
            "Elizaveta-Shtukaturova",
            "nate20Elizaveta21"
        ),
        User(
            "kolykhalova.o@mail.ru",
            "Olga A. Kolykhalova",
            "Olga-Kolykhalova",
            "nate20Olga21"
        ),
        User(
            "dokamtv@yahoo.com",
            "Matsalak Tatiana",
            "Tatiana-Matsalak",
            "nate20Tatiana21"
        ),
        User(
            "marialinta0@gmail.com",
            "Komarova Maria Viktorovna",
            "Maria-Komarova",
            "nate20Maria21"
        ),
        //10
        User(
            "evgeniavorontsova@yandex.ru",
            "Evgenia Vorontsova",
            "Evgenia-Vorontsova",
            "nate20Evgenia21"
        ),
        User(
            "iashina.ov@mipt.ru",
            "Yashina",
            "Olga-Yashina",
            "nate20Olga21"
        ),
        User(
            "irinaffl@mail.ru",
            "Irina Basova",
            "Irina-Basova",
            "nate20Irina21"
        ),
        User(
            "sonyachita14@mail.ru",
            "Simatova Sofya Andreevna",
            "Sofya-Simatova",
            "nate20Sofya21"
        ),
        User(
            "peressada@gmail.com",
            "Elena Peresada",
            "Elena-Peresada",
            "nate20Elena21"
        ),
        User(
            "ekaterina.shtokolova@gmail.com",
            "Ekaterina Shtokolova",
            "Ekaterina-Shtokolova",
            "nate20Ekaterina21"
        ),
        User(
            "catish.nikitina@gmail.com",
            "Ekaterina Nikitina",
            "Ekaterina-Nikitina",
            "nate20Ekaterina21"
        ),
        User(
            "marija-pavlova1993@yandex.ru",
            "Maria Pavlova",
            "Maria-Pavlova",
            "nate20Maria21"
        ),
        User(
            "ujushka@mail.ru",
            "Uliana Ivanova",
            "Uliana-Ivanova",
            "nate20Uliana21"
        ),
        User(
            "morozovaen@s5gub.ru",
            "Yekaterina Morozova",
            "Yekaterina-Morozova1",
            "nate20Yekaterina21"
        ),
        //10
        User(
            "sofia-sophia@mail.ru",
            "Sofiya",
            "Sofiya-Polyakova",
            "nate20Sofiya21"
        ),
        User(
            "babinael@mail.ru",
            "Elena Babina",
            "Elena-Babina",
            "nate20Elena21"
        ),
        User(
            "Diamond_like@mail.ru",
            "Natalia Pernik",
            "Natalia-Pernik",
            "nate20Natalia21"
        ),
        User(
            "poteryakhinain@gmail.com",
            "Inna Poteryakhina",
            "Inna-Poteryakhina",
            "nate20Inna21"
        ),
        User(
            "aberokhina@gmail.com",
            "Alexandra Erokhinks",
            "Alexandra-Erokhinks",
            "nate20Alexandra21"
        ),
        User(
            "mkreer@mail.ru",
            "Kreer Michael",
            "Michael-Kreer",
            "nate20Michael21"
        ),
        User(
            "dormir@mail.ru",
            "Marina Dyachuk",
            "Marina-Dyachuk",
            "nate20Marina21"
        ),
        User(
            "hgva2017@gmail.com",
            "Asmik",
            "Asmik-Grigoryan",
            "nate20Asmik21"
        ),
        User(
            "eshadrova1@gmail.com",
            "Anastasia Shadrova",
            "Anastasia-Shadrova",
            "nate20Anastasia21"
        ),
        User(
            "ostvera@mail.ru",
            "Minyar-Beloroucheva Alla Petrovna",
            "Alla-Minyar-Beloroucheva",
            "nate20Alla21"
        ),
        //10
        User(
            "corazonx@mail.ru",
            "Kolyshnitsyna Tatiana",
            "Tatiana-Kolyshnitsyna",
            "nate20Tatiana21"
        ),
        User(
            "ya.mashq2013@ya.ru",
            "Kochneva Maria Maksimovna",
            "Maria-Kochneva",
            "nate20Maria21"
        ),
        User(
            "svetlana-s-sotnikova@yandex.ru",
            "Svetlana Sotnikova",
            "Svetlana-Sotnikova",
            "nate20Svetlana21"
        ),
        User(
            "AnatolyShilov@yandex.ru",
            "Anatolyb Shilov",
            "Anatolyb-Shilov",
            "nate20Anatolyb21"
        ),
        User(
            "Levchenko_v2004@mail.ru",
            "Viktoriya Levchenko",
            "Viktoriya-Levchenko",
            "nate20Viktoriya21"
        ),
        User(
            "m.bolotina@mail.ru",
            "Marina A. Bolotina",
            "Marina-Bolotina",
            "nate20Marina21"
        ),
        User(
            "alexsolovyova@gmail.com",
            "Aleksandra Mosesohn",
            "Aleksandra-Mosesohn",
            "nate20Aleksandra21"
        ),
        User(
            "natkabor@mail.ru",
            "Natalya Borodina",
            "Natalya-Borodina",
            "nate20Natalya21"
        ),
        User(
            "irno2012@yandex.ru",
            "Irina Novitskaya",
            "Irina-Novitskaya",
            "nate20Irina21"
        ),
        User(
            "Natalya.a4kasova@yandex.ru",
            "Natalya Achkasova",
            "Natalya-Achkasova",
            "nate20Natalya21"
        ),
        //10
        User(
            "jartemenkova@gmail.com",
            "Julia Artemenkova",
            "Julia-Artemenkova",
            "nate20Julia21"
        ),
        User(
            "koltakova2007@yandex.ru",
            "Koltakova Sofya",
            "Sofya-Koltakova",
            "nate20Sofya21"
        ),
        User(
            "larpro@rambler.ru",
            "Larisa Prokhorova",
            "Larisa-Prokhorova",
            "nate20Larisa21"
        ),
        User(
            "tfomina67@mail.ru",
            "Tatiana Fomina",
            "Tatiana-Fomina",
            "nate20Tatiana21"
        ),
        User(
            "ag31121999@yandex.ru",
            "Anastasia Gorokhova",
            "Anastasia-Gorokhova",
            "nate20Anastasia21"
        ),
        User(
            "zhukova.alina@mail.ru",
            "Lugova Alina",
            "Alina-Lugova",
            "nate20Alina21"
        ),
        User(
            "natavs@list.ru",
            "Nataliya Solovyeva",
            "Nataliya-Solovyeva",
            "nate20Nataliya21"
        ),
        User(
            "pla85@mail.ru",
            "Lyubov Savelyeva",
            "Lyubov-Savelyeva",
            "nate20Lyubov21"
        ),
        User(
            "ddklim@mail.ru",
            "DMITRY KLIMENTEV",
            "DMITRY-KLIMENTEV",
            "nate20DMITRY21"
        ),
        User(
            "v_klimentyeva@mail.ru",
            "Viktoria Klimentyeva",
            "Viktoria-Klimentyeva",
            "nate20Viktoria21"
        ),
        //10
        User(
            "iyakt.misis@mail.ru",
            "Merkulova G. Svetlana",
            "Svetlana-Merkulova",
            "nate20Svetlana21"
        ),
        User(
            "av.scherbakova@misis.ru",
            "Scherbakova Alla Vladimirovna",
            "Alla-Scherbakova",
            "nate20Alla21"
        ),
        User(
            "elena.sm83@gmail.com",
            "Samofalova Elena",
            "Elena-Samofalova",
            "nate20Elena21"
        ),
        User(
            "rossog@rambler.ru",
            "Olga G. Rossikhina",
            "Olga-Rossikhina",
            "nate20Olga21"
        ),
        User(
            "eknatalya@yandex.ru",
            "Natalya Fyodorovna Ekhlakova",
            "Natalya-Ekhlakova",
            "nate20Natalya21"
        ),
        User(
            "elena.melnikova@mail.tsu.ru",
            "Elena Aleksandrovna Melnikova",
            "Elena-Melnikova",
            "nate20Elena21"
        ),
        User(
            "lucy2306@mail.ru",
            "Liudmila Mitchell",
            "Liudmila-Mitchell",
            "nate20Liudmila21"
        ),
        User(
            "ksenofontovaalina@gmail.com",
            "Ksenofontova Aina Andreevna",
            "Alina-Ksenofontova",
            "nate20Alina21"
        ),
        User(
            "zaharowa.zahar@yandex.ru",
            "Anna Zakharova",
            "Anna-Zakharova",
            "nate20Anna21"
        ),
        User(
            "jain15@mail.ru",
            "Malkina Evgeniya Andreevna",
            "Evgeniya-Malkina",
            "nate20Evgeniya21"
        ),
        //10
        User(
            "len.femme@yandex.ru",
            "Popkova Elena",
            "Elena-Popkova",
            "nate20Elena21"
        ),
        User(
            "yavdeeva@mail.ru",
            "Yulia Avdeeva",
            "Yulia-Avdeeva",
            "nate20Yulia21"
        ),
        User(
            "lvv187@mail.ru",
            "Victoria Lopatinskaya",
            "Victoria-Lopatinskaya",
            "nate20Victoria21"
        ),
        User(
            "natas2002@yandex.ru",
            "Natalia V. Smirnova",
            "Natalia-Smirnova",
            "nate20Natalia21"
        ),
        User(
            "lgorodet@gmail.com",
            "Ludmila Gorodetskaya",
            "Ludmila-Gorodetskaya",
            "nate20Ludmila21"
        ),
        User(
            "sergaeva@gmail.com",
            "Yulia Sergaeva",
            "Yulia-Sergaeva",
            "nate20Yulia21"
        ),
        User(
            "veragrishenko@rambler.ru",
            "Vera D. Grishenko",
            "Vera-Grishenko",
            "nate20Vera21"
        ),
        User(
            "svetshum13@gmail.com",
            "Svetlana S. Shumbasova",
            "Svetlana-Shumbasova",
            "nate20Svetlana21"
        ),
        User(
            "svetareztsova@rambler.ru",
            "Svetlana Reztsova",
            "Svetlana-Reztsova",
            "nate20Svetlana21"
        ),
        User(
            "natpalkina@rambler.ru",
            "Natalya Palkina",
            "Natalya-Palkina",
            "nate20Natalya21"
        ),
        //10
        User(
            "divinity77@mail.ru",
            "Khmyrova Irina Alexandrovna",
            "Irina-Khmyrova",
            "nate20Irina21"
        ),
        User(
            "sabina.yastrebova@mail.ru",
            "Sabina Yastrebova",
            "Sabina-Yastrebova",
            "nate20Sabina21"
        ),
        User(
            "orchide@yandex.ru",
            "Daria Ignatova",
            "Daria-Ignatova",
            "nate20Daria21"
        ),
        User(
            "pgoncharenko@yandex.ru",
            "Polina Goncharenko",
            "Polina-Goncharenko",
            "nate20Polina21"
        ),
        User(
            "belkinanastya2000@yandex.ru",
            "Anastasiia Belkina",
            "Anastasiia-Belkina",
            "nate20Anastasiia21"
        ),
        User(
            "evgeniya-bulina@yandex.ru",
            "Bulina Evgeniya Nikolaevna",
            "Evgeniya-Bulina",
            "nate20Evgeniya21"
        ),
        User(
            "yazykova-n@mail.ru",
            "Natalia Yazykova",
            "Natalia-Yazykova",
            "nate20Natalia21"
        ),
        User(
            "maria.brisk@bc.edu",
            "Maria Brisk",
            "Maria-Brisk",
            "nate20Maria21",
            "Speaker"
        ),
        User(
            "robert.klassen@york.ac.uk",
            "Robert Klassen",
            "Robert-Klassen",
            "nate20Robert21",
            "Speaker"
        ),
        User(
            "marklen@konurbaev.ru",
            "Marklen Konurbaev",
            "Marklen-Konurbaev",
            "nate20Marklen21",
            "Speaker"
        ),
        //10
        User(
            "marthasidury.christiansen@utsa.edu",
            "Martha Sidury Christiansen",
            "Martha-Sidury-Christiansen",
            "nate20Martha21",
            "Speaker"
        ),
        User(
            "sashakr@hotmail.com",
            "Alexander Kravchenko",
            "Alexander-Kravchenko",
            "nate20Alexander21",
            "Speaker"
        ),
        User(
            "blainesmith@arizona.edu",
            "Blaine Smith",
            "Blaine-Smith",
            "nate20Blaine21",
            "Speaker"
        ),
        User(
            "drcowinj@gmail.com",
            "Jasmin Cowin",
            "Jasmin-Cowin",
            "nate20Jasmin21",
            "Speaker"
        ),
        User(
            "bauters@tlu.ee",
            "Merja Bauters",
            "Merja-Bauters",
            "nate20Merja21",
            "Speaker"
        ),
        User(
            "giedre.vasil@gmail.com",
            "Giedre Vasiliuskaite",
            "Giedre-Vasiliuskaite",
            "nate20Giedre21",
            "Speaker"
        ),
        User(
            "eevans@bkc.ru",
            "Edwards Evans",
            "Edwards-Evans",
            "nate20Edwards21",
            "Speaker"
        ),
        User(
            "erken.e@mes.ru",
            "Emily Erken",
            "Emily-Erken",
            "nate20Emily21"
        ),
        User(
            "sjiang24@ncsu.edu",
            "Shiyan Jiang",
            "Shiyan-Jiang",
            "nate20Shiyan21",
            "Speaker"
        ),
        //10
        User(
            "edejong@coe.ufl.edu",
            "Ester de Jong",
            "Ester-de-Jong",
            "nate20Ester21",
            "Speaker"
        ),
        User(
            "simonbrooks.t21@btinternet.com",
            "Simon Brooks",
            "Simon-Brooks",
            "nate20Simon21",
            "Speaker"
        ),
        User(
            "tgolechkova@nes.ru",
            "Tatiana Golechkova",
            "Tatiana-Golechkova",
            "nate20Tatiana21",
            "Speaker"
        ) //100
    )
    for (user in users)
        addNewUser(user)
}