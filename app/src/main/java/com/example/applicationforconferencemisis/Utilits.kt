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

fun String.asDate(): String {
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
            "Conference Opening: Svetlana G. Ter-Minasova \n" +
                    "Opening Plenary Talks \n" +
                    "Peter Watkins \n" +
                    "Reading Evolution: Helping Learners to Read Online \n" +
                    "Kelley Calvert \n" +
                    "it wrong: Maintaining Motivation and Preventing Teacher Burnout with Student-centered Instruction",
            "10:30-12:30"
        ),
        MainSchedule("Lunch", "12:30-13:30"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "13:30-15:00"),
        MainSchedule("Break", "15:00-15:15"),
        MainSchedule("Concurrent Sessions (see detailed schedule)", "15:15-16:45"),
        MainSchedule("Break", "16:45-17:00"),
        MainSchedule(
            "MISIS-CUP 10-year Partnership Anniversary Roundtable:\n" +
                    "What are the Success Criteria and Impact of Educational Innovations?",
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
                    "10:30-11:10 – Susana Anton (Cambridge University Press) – Learning to Learn with Cambridge One \n" +
                    "11:10-11:30 – Julia Khukalenko, Vera Iushina (Far Eastern Federal University) – VARVARA: Virtual Reality for Learning English \n" +
                    "11:30-12:10 – Alexey Konobeiev – Standards-based Development of ESL Online Courses; Mikhail Sverdlov – Education 4.0: Data Driven Approach for Education Product Development (Skyeng) \n" +
                    "12:10-12:30 – Maria Anikina (UCHi.RU) – Improving Educational Programs Considering Motivation and Students` Emotional Experience: the Experience of Uchi.Doma", "10:30-12:30"
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
            "Elena Solovova Honorary Readings (in Russian):\n" +
                    "Kuzmina L.G.\n" +
                    "E.N. Solovova and the Russian methodology of ELT\n" +
                    "Markova E.S.\n" +
                    "E.N. Solovova and the practice of ELT", "11:00-12:30"
        ),
        MainSchedule("Break", "12:30-13:00"),
        MainSchedule(
            "Closing Plenary Talk:\n" +
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
            "8:40 - 9:25",
            "Peresada Elena"
        ),
        Conference(
            "2",
            "New Frontiers of Testing and Assessment",
            "Energise, Optimise. Digitalise.",
            "8:40 - 9:25",
            "Kovaleva Yelena, Mogunova Elena, Petrova Natalia"
        ),
        Conference(
            "3",
            "Teacher Development",
            "Developing English speaking and writing skills of B1-C1 students by means of Instagram.",
            "8:40 - 9:25",
            "Kalintsev Alexey"
        ),
        Conference(
            "4",
            "Digital Methodology",
            "Online vs. offline: 3 simple tools to make your lessons more interactive.",
            "8:40 - 9:25",
            "Khodakova Anastasia"
        ),
        Conference(
            "5",
            "Digital Methodology",
            "Running teacher training online.",
            "9:35-10:20",
            "Brooks Simon"
        ),
        Conference(
            "6",
            "Digital Methodology",
            "Teaching speaking and critical thinking skills in a low-tech online environment: approaches, tasks, online instruments.",
            "9:35-10:20",
            "Titova Svetlana "
        ),
        Conference(
            "7",
            "Teacher Development",
            "Collaborative design tools in learning.",
            "9:35-10:20",
            "Bauters Merja"
        ),
        Conference(
            "8",
            "Teacher Development",
            "Building Reflexes in a Foreign Language.",
            "9:35-10:20",
            "Marcq Antoine"
        ),
        Conference(
            "9",
            "Teacher Development",
            "It is while writing, that we are thinking most.",
            "9:35-10:20",
            "Ivanova Tatiana"
        ),
        Conference(
            "10",
            "Learner Diversity and Inclusion",
            "International career planning: preparing students for the global workplace.",
            "9:35-10:20",
            "Mitchell Peter"
        ),
        Conference(
            "11",
            "English Language Research",
            "Acquiring talent & improving performance through AI powered English testing.",
            "13:30-14:15",
            "Pandey Namita"
        ),
        Conference(
            "12",
            "Digital Methodology",
            "Teaching pronunciation digitally.",
            "14:15-15:00",
            "Gordyshevskaya Polina"
        ),
        Conference(
            "13",
            "English Language Research",
            "Innovation, collaboration, transformation: education for a connected world.",
            "15:00-15:45",
            "Cowin Jasmin"
        ),
        Conference(
            "14",
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
            "8:40 - 9:25",
            "Howard Rob"
        ),
        Conference(
            "2",
            "Teacher Development",
            "Integrating critical thinking instruction into the Unified National Exam training programme.",
            "8:40 - 9:25",
            "Kapturova Evgenia"
        ),
        Conference(
            "3",
            "New Frontiers of Testing and Assessment",
            "Mind(full)ness: keep calm and learn.",
            "8:40 - 9:25",
            "Dilara Louise Hibbs"
        ),
        Conference(
            "4",
            "Teacher Development",
            "Mastering research competence & confidence: the chief editor’s view.",
            "8:40 - 9:25",
            "Tverdokchlebova Irina"
        ),
        Conference(
            "5",
            "Digital Methodology",
            "Project-based learning: digital tools worth adopting.",
            "8:40 - 9:25",
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
        Conference(
            "12",
            "Intercultural Communication",
            "TOPIC TBC",
            "14.15-14.30",
            "Rotko Olga"),
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
        Conference(
            "15",
            "English Language Research",
            "To the problem of revealing national peculiarity of semantics.",
            "14.45-15.00",
            "Sternina Marina"
        ),
        Conference(
            "16",
            "Teacher Development",
            "English in a multilingual world: implications for teachers.",
            "15.15-15.30",
            "De Jong Ester"
        ),
        Conference(
            "17",
            "English Language Research",
            "Methodological issues in digital multimodal literacy research.",
            "15.15-15.30",
            "Yi Youngjoo"
        ),
        Conference(
            "18",
            "Digital Methodology",
            "Expanding meaning-making possibilities through multimodal composition.",
            "15.15-15.30",
            "Smith Blaine"
        ),
        Conference(
            "19",
            "Teacher Development",
            "Professional self-development of teachers in online environment.",
            "15.30-15.45",
            "Avdeeva Yulia, Grashchenkova Galina"
        ),
        Conference(
            "20",
            "English Language Research",
            "Information literacy for classroom research.",
            "15.30-15.45",
            "Safonkina Olga"
        ),
        Conference(
            "21",
            "Digital Methodology",
            "Blended Learning via Distance Learning: New Approaches in Teaching English.",
            "15.30-15.45",
            "Igonina Galina"
        ),
        Conference(
            "22",
            "Teacher Development",
            "Designing the course \"ICT in Education\" for beginning ESL teachers at Astrakhan State University.",
            "15.45-16.00",
            "Galichkina Elena"
        ),
        Conference(
            "23",
            "English Language Research",
            "CLIL-approach as extra motivation for students aged 10-12.",
            "15.45-16.00",
            "Yepova Olga"
        ),
        Conference(
            "24",
            "Digital Methodology",
            "Hybrid environment: managing a class.",
            "15.45-16.00",
            "Ponidelko Lyubov, Petrova Natalya"
        ),
        Conference(
            "25",
            "Teacher Development",
            "In-service training for ELT: project-based learning with authentic mass media.",
            "16.00-16.15",
            "Dvorghets Olga"
        ),
        Conference(
            "26",
            "English Language Research",
            "Mind mapping and its implementation into educational process.",
            "16.00-16.15",
            "Krivenko Lyudmila"
        ),
        Conference(
            "27",
            "Digital Methodology",
            "The peculiarities of teaching ESP online only.",
            "16.00-16.15",
            "Klochko Konstantin"
        ),
        Conference(
            "28",
            "Teacher Development",
            "Adapting teachers to changes in the curriculum.",
            "16.15-16.30",
            "Kiryukina Natalya"
        ),
        Conference(
            "29",
            "English Language Research",
            "Linguodidactic features of teaching English at the primary level.",
            "16.15-16.30",
            "Skachkova Sofia"
        ),
        Conference(
            "30",
            "Digital Methodology",
            "Mobile learning and foreign language teaching. How do they align?",
            "16.15-16.30",
            "Golova Ekaterina"
        ),
        Conference(
            "31",
            "English Language Research",
            "TOPIC TBC",
            "16.30-16.45",
            "Kolesnikova Marina"
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
            "nate20Tatyana21",
            "Speaker"
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
            "nate20Olga21",
            "Speaker"
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
            "nate20Anastasia21",
            "Speaker"
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
            "nate20Galina21",
            "Speaker"
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
            "nate20Irina21",
            "Speaker"
        ),
        User(
            "okuznetchik@yandex.ru",
            "Olga Safonkina",
            "Olga-Safonkina",
            "nate20Olga21",
            "Speaker"
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
            "nate20Natalya21",
            "Speaker"
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
            "nate20Polina21",
            "Speaker"
        ),
        User(
            "sckachckova.sofi@gmail.com",
            "Sofia Skachkova",
            "Sofia-Skachkova",
            "nate20Sofia21",
            "Speaker"
        ),
        User(
            "lisi4ka1602@yandex.ru",
            "Marina Kolesnikova",
            "Marina-Kolesnikova",
            "nate20Marina21",
            "Speaker"
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
            "nate20Olga21",
            "Speaker"
        ),
        User(
            "elena.galichkina@mail.ru",
            "Elena Galichkina",
            "Elena-Galichkina",
            "nate20Elena21",
            "Speaker"
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
            "Speaker"
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
            "nate20Svetlana21",
            "Speaker"
        ),
        User(
            "gritsenko@inbox.ru",
            "Elena Gritsenko",
            "Elena-Gritsenko",
            "nate20Elena21",
            "Speaker"
        ),
        User(
            "barabashka84@mail.ru",
            "Irina Barabushka",
            "Irina-Barabushka",
            "nate20Irina21",
            "Speaker"
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
            "nate20Anastasia21",
            "Speaker"
        ),
        User(
            "osdvor@rambler.ru",
            "Olga Solomonovna Dvorghets",
            "Olga-Solomonovna",
            "nate20Olga21",
            "Speaker"
        ),
        User(
            "inaviri@gmail.com",
            "Irina I. Vasilyeva",
            "Irina-Vasilyeva",
            "nate20Irina21",
            "Speaker"
        ),
        User(
            "ats584793@mail.ru",
            "Arkhipova Tatiana",
            "Tatiana-Arkhipova",
            "nate20Tatiana21",
            "Speaker"
        ),
        User(
            "nataigolkina@mail.ru",
            "Natalia Igolkina",
            "Natalia-Igolkina",
            "nate20Natalia21",
            "Speaker"
        ),
        User(
            "valenttif@gmail.com",
            "Valentina Igorevna Fedosova",
            "Valentina-Fedosova",
            "nate20Valentina21",
            "Speaker"
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
            "nate20Elizaveta21",
            "Speaker"
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
            "nate20Ekaterina21",
            "Speaker"
        ),
        User(
            "konstklochko@gmail.com",
            "Konstantin A. Klochko",
            "Konstantin-Klochko",
            "nate20Konstantin21",
            "Speaker"

        ),
        User(
            "gorelovachernyh@mail.ru",
            "Natalie Gorelova",
            "Natalie-Gorelova",
            "nate20Natalie21",
            "Speaker"
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
            "nate20Lyudmila21",
            "Speaker"
        ),
        User(
            "polina.ermakova.misis@gmail.com",
            "Polina Ermakova",
            "Polina-Ermakova",
            "nate20Polina21",
            "Speaker"
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
            "nate20Sidury21",
            "Speaker"
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
            "Speaker"
        ),
        User(
            "galina-the-happy@mail.ru",
            "Galina Grashchenkova",
            "Galina-Grashchenkova",
            "nate20Galina21",
            "Speaker"
        ),
        User(
            "irysya.polukhina@gmail.com",
            "Irina Polukhina",
            "Irina-Polukhina",
            "nate20Irina21",
            "Speaker"
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
            "nate20Anna21",
            "Speaker"
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
            "nate20Petr21",
            "Speaker"
        ),
        User(
            "lalaith@mail.ru",
            "Liubov Ponidelko",
            "Liubov-Ponidelko",
            "nate20Liubov21",
            "Speaker"
        ),
        User(
            "natalie_ye@mail.ru",
            "Petrova Natalya",
            "Natalya-Petrova",
            "nate20Natalya21",
            "Speaker"
        ),
        User(
            "veranovik@mail.ru",
            "Novikova Vera Pavlovna",
            "Vera-Novikova",
            "nate20Vera21",
            "Speaker"
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
            "nate20Yelena21",
            "Speaker"
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
            "nate20Lyudmila21",
            "Speaker"
        ),
        User(
            "oyepova@mail.ru",
            "Olga Yepova",
            "Olga-Yepova",
            "nate20Olga21",
            "Speaker"
        ),
        User(
            "ivostrikova@mail.ru",
            "Irina Vostrikova",
            "Irina-Vostrikova",
            "nate20Irina21",
            "Speaker"
        ),
        User(
            "dyankova@nbu.bg",
            "Diana Yankova",
            "Diana-Yankova",
            "nate20Diana21",
            "Speaker"
        ),
        User(
            "nataliakiryukhina@yandex.ru",
            "NATALIA KIRYUKHINA",
            "NATALIA-KIRYUKHINA",
            "nate20NATALIA21",
            "Speaker"
        ),
        User(
            "shilova_ivsu@mail.ru",
            "Ekaterina Shilova",
            "Ekaterina-Shilova",
            "nate20Ekaterina21",
            "Speaker"
        ),
        User(
            "tatianazaitseva2013@yandex.ru",
            "Tatiana A. Zaitseva",
            "Tatiana-Zaitseva",
            "nate20Tatiana21",
            "Speaker"
        ),
        User(
            "ikharlamenko@yandex.ru",
            "Kharlamenko Inna Vladimirovna",
            "Inna-Kharlamenko",
            "nate20Inna21",
            "Speaker"
        ),
        User(
            "janet.golding@languagecert.org",
            "Janet Golding",
            "Janet-Golding",
            "nate20Janet21",
            "Speaker"
        ),
        User(
            "ekuzsocial@gmail.com",
            "Elena Kuznetsova",
            "Elena-Kuznetsova",
            "nate20Elena21",
            "Speaker"
        ),
        User(
            "umk_kizt@mail.ru",
            "Panchenkova Mariia",
            "Mariia-Panchenkova",
            "nate20Mariia21",
            "Speaker"
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
            "nate20Elena21",
            "Speaker"
        ),
        User(
            "anzhelika.vladyko@gmail.com",
            "Anzhelika Vladyko",
            "Anzhelika-Vladyko",
            "nate20Anzhelika21",
            "Speaker"
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
            "nate20Anna21",
            "Speaker"
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
            "nate20Olga21",
            "Speaker"
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
            "nate20Aleksey21",
            "Speaker"
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
            "nate20Maria21",
            "Speaker"
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
            "nate20Olga21",
            "Speaker"
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
            "nate20Yulia21",
            "Speaker"
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
            "nate20Anastasiia21",
            "Speaker"
        ),
        User(
            "evgeniya-bulina@yandex.ru",
            "Bulina Evgeniya Nikolaevna",
            "Evgeniya-Bulina",
            "nate20Evgeniya21",
            "Speaker"
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
        ),
        User(
            "sternina@vmail.ru",
            "Marina Sternina",
            "Marina-Sternina",
            "nate20Marina21"
        ),
        User(
            "mshumakova2007@yandex.ru",
            "Marina Shumakova",
            "Marina-Shumakova",
            "nate20Marina21"
        ),
        User(
            "rob-howard@outlook.com",
            "Rob Howard",
            "Rob-Howard",
            "nate20Rob21",
            "Speaker"
        ),
        User(
            "enzo_scaglietti@mail.ru",
            "Guliya Shaykhutdinova",
            "Guliya-Shaykhutdinova",
            "nate20Guliya21"
        ),
        User(
            "frolovanh@gmail.com",
            "Natalia Frolova",
            "Natalia-Frolova",
            "nate20Natalia21"
        ),
        User(
            "leboldyreva@yandex.ru",
            "Elena Boldyreva",
            "Elena-Boldyreva",
            "nate20Elena21"
        ),
        User(
            "mmamaew87@gmail.com",
            "Mikhail Mamaev",
            "Mikhail-Mamaev",
            "nate20Mikhail21"
        ),
        User(
            "engl.natalya@mail.ru",
            "Natalya Lavrentyeva",
            "Natalya-Lavrentyeva",
            "nate20Natalya21",
            "Speaker"
        ),
        User(
            "Olga.rotko@gmail.com",
            "Olga Rotko",
            "Olga-Rotko",
            "nate20Olga21",
            "Speaker"
        ),
        User(
            "i.r.abdulmyanova@gmail.com",
            "Indira Abdulmianova",
            "Indira-Abdulmianova",
            "nate20Indira21"
        ),
        User(
            "sunnymood77@hotmail.com",
            "Tatiana Margaryan",
            "Tatiana-Margaryan",
            "nate20Tatiana21"
        ),
        User(
            "elenasilikova@yandex.ru",
            "Elena Dmitrieva",
            "Elena-Dmitrieva",
            "nate20Elena21"
        ),
        User(
            "violettap1@mail.ru",
            "Violetta V. Petrova",
            "Violetta-Petrova",
            "nate20Violetta21"
        ),
        User(
            "tuchkova_m0903@mail.ru",
            "Marina Tuchkova",
            "Marina-Tuchkova",
            "nate20Marina21"
        ),
        User(
            "barnoavezova@hotmail.com",
            "Barno Avezova",
            "Barno-Avezova",
            "nate20Barno21"
        ),
        User(
            "stepanovany@yandex.ru",
            "Natalia Stepanova",
            "Natalia-Stepanova",
            "nate20Natalia21"
        ),
        User(
            "KateP13@rambler.ru",
            "Ekaterina Plataeva",
            "Ekaterina-Plataeva",
            "nate20Ekaterina21"
        ),
        User(
            "m.kopylovskaya@spbu.ru",
            "Maria Kopylovskaya",
            "Maria-Kopylovskaya",
            "nate20Maria21"
        ),
        User(
            "nataliya.sonina@bk.ru",
            "Nataliya Sonina",
            "Nataliya-Sonina",
            "nate20Nataliya21"
        ),
        User(
            "kuzminahome@mail.ru",
            "Larisa Kuzmina",
            "Larisa-Kuzmina",
            "nate20Larisa21"
        ),
        User(
            "poserg@bk.ru",
            "Polina Sergienko",
            "Polina-Sergienko",
            "nate20Polina21"
        ),
        User(
            "yskugarova@cambridge.org",
            "Yulia Skugarova",
            "Yulia-Skugarova",
            "nate20Yulia21"
        ),
        User(
            "kzhura@cambridge.org",
            "Ekaterina Zhura",
            "Ekaterina-Zhura",
            "nate20Ekaterina21"
        ),
        User(
            "iputro@cambridge.org",
            "Irina Putro",
            "Irina-Putro",
            "nate20Irina21"
        ),
        User(
            "atatosyan@cambridge.org",
            "Angela Tatosyan",
            "Angela-Tatosyan",
            "nate20Angela21"
        ),
        User(
            "lkozhevnikova@cambridge.org",
            "Ludmila Kozhevnikova",
            "Ludmila-Kozhevnikova",
            "nate20Ludmila21"
        ),
        User(
            "hugh@lexicallab.com",
            "Hugh Dellar",
            "Hugh-Dellar",
            "nate20Hugh21",
            "Speaker"
        ),
        User(
            "m.sverdlov@skyeng.ru",
            "Mikhail Sverdlov",
            "Mikhail-Sverdlov",
            "nate20Mikhail21"
        ),
        User(
            "alexey_konobeiev@mail.ru",
            "Alexey Konobeev",
            "Alexey-Konobeev",
            "nate20Alexey21"
        ),
        User(
            "eduvr@vrnti.ru",
            "Yuliya Khukalenko",
            "Yuliya-Khukalenko",
            "nate20Yuliya21"
        ),
        User(
            "mironovaolga836@gmail.com",
            "Olga Mironova",
            "Olga-Mironova",
            "nate20Olga21"
        ),
        User(
            "engjourn@gmail.com",
            "Elena Markova",
            "Elena-Markova",
            "nate20Elena21"
        ),
        User(
            "natashaless@rambler.ru",
            "Natalie Les",
            "Natalie-Les",
            "nate20Natalie21"
        ),
        User(
            "karpova_elena@mail.ru",
            "Elena Polyakova",
            "Elena-Polyakova",
            "nate20Elena21"
        ),
        User(
            "english_profi@mail.ru",
            "Iuliia Maksimova",
            "Iuliia-Maksimova",
            "nate20Iuliia21"
        ),
        User(
            "natalya.ivanovna.marchenko@gmail.com",
            "Natalya Ivanovna Marchenko",
            "Natalya-Marchenko",
            "nate20Natalya21"
        ),
        //new users
        User(
            "smileytan@list.ru",
            "Tatiana Malysheva",
            "Tatiana-Malysheva",
            "nate20Tatiana21"
        ),
        User(
            "evgeniya.kapp@gmail.com",
            "Evgenia Kapturova",
            "Evgenia-Kapturova",
            "nate20Evgenia21"
        ),
        User(
            "botogova@mail.ru",
            "Ekaterina Botogova",
            "Ekaterina-Botogova",
            "nate20Ekaterina21"
        ),
        User(
            "helensivtseva@mail.ru",
            "Oxana Sivtseva",
            "Oxana-Sivtseva",
            "nate20Oxana21"
        ),
        User(
            "annabakhter@yandex.ru",
            "Anna Bakhter",
            "Anna-Bakhter",
            "nate20Anna21"
        ),
        User(
            "irbakh@mail.ru",
            "Irina Bakhmetieva",
            "Irina-Bakhmetieva",
            "nate20Irina21"
        ),
        User(
            "dialex09@mail.ru",
            "Irina Dyachenko",
            "Irina-Dyachenko",
            "nate20Irina21"
        ),
        User(
            "serafima.yakovleva.99@mail.ru",
            "Serafima Falshtinskaya",
            "Serafima-Falshtinskaya",
            "nate20Serafima21"
        ),
        User(
            "svetlana_chspu@mail.ru",
            "Svetlana Sannikova",
            "Svetlana-Sannikova",
            "nate20Svetlana21"
        ),
        User(
            "jiganowa@yandex.ru",
            "Anastasia Zhiganova",
            "Anastasia-Zhiganova",
            "nate20Anastasia21"
        ),
        User(
            "yanasmirnova@hotmail.com",
            "Uliana V. Smirnova",
            "Uliana-Smirnova",
            "nate20Uliana21"
        ),
        User(
            "sefl@mail.ru",
            "Olga Minina",
            "Olga-Minina",
            "nate20Olga21"
        ),
        User(
            "lug-evgenia@mail.ru",
            "Evgeniya Luganskaya",
            "Evgeniya-Luganskaya",
            "nate20Evgeniya21"
        ),
        User(
            "v.m.evdash@utmn.ru",
            "Valeria Evdash",
            "Valeria-Evdash",
            "nate20Valeria21"
        ),
        User(
            "MarinaDenel@yandex.ru",
            "Marina Denel",
            "Marina-Denel",
            "nate20Marina21"
        ),
        User(
            "sergeyumerenkov@mail.ru",
            "Sergey Umerenkov",
            "Sergey-Umerenkov",
            "nate20Sergey21"
        ),
        User(
            "anna-umerenkova@yandex.ru",
            "Anna Umerenkova",
            "Anna-Umerenkova",
            "nate20Anna21"
        ),
        User(
            "apavelchik@gmail.com",
            "Anna Kireeva",
            "Anna-Kireeva",
            "nate20Anna21"
        ),
        User(
            "elena.satarowa@yandex.ru",
            "Elena Satarova",
            "Elena-Satarova",
            "nate20Elena21"
        ),
        User(
            "gulov@tea4er.org",
            "Artem Gulov",
            "Artem-Gulov",
            "nate20Artem21"
        ),
        User(
            "m.panarina@mail.ru",
            "Maria Panarina",
            "Maria-Panarina",
            "nate20Maria21",
        ),
        User(
            "mpsu08@mail.ru",
            "Tatiana Lyugaeva",
            "Tatiana-Lyugaeva",
            "nate20Tatiana21",
        ),
        User(
            "aivengomag@gmail.com",
            "Maria Fokina",
            "Maria-Fokina",
            "nate20Maria21"
        ),
        User(
            "dos@iseykt.com",
            "James Scholl",
            "James-Scholl",
            "nate20James21"
        ),
        User(
            "listopadova_m@mail.ru",
            "Maria Listopadova",
            "Maria-Listopadova",
            "nate20Maria21"
        ),
        User(
            "juliajuzhakova@yandex.ru",
            "Julia Yuzhakova",
            "Julia-Yuzhakova",
            "nate20Julia21"
        ),
        User(
            "stitova3@gmail.com",
            "Svetlana Titova",
            "Svetlana-Titova",
            "nate20Svetlana21"
        ),
        User(
            "verb7@mail.ru",
            "Maria Verbitskaya",
            "Maria-Verbitskaya",
            "nate20Maria21"
        ),
        User(
            "sgtermin@mail.ru",
            "Svetlana Ter-Minasova",
            "Svetlana-Ter-Minasova",
            "nate20Svetlana21"
        ),
        User(
            "elena-poletaeva@mail.ru",
            "Elena Poletaeva",
            "Elena-Poletaeva",
            "nate20Elena21"
        ),
        User(
            "helenpetrova@list.ru",
            "Elena Tikhomirova",
            "Elena-Tikhomirova",
            "nate20Elena21"
        ),
        User(
            "ekaterenta90@gmail.com",
            "Ekaterina Terenteva",
            "Ekaterina-Terenteva",
            "nate20Ekaterina21"
        ),
        User(
            "17vernonsemperviret47@gmail.com",
            "Tatyana Kalacheva",
            "Tatyana-Kalacheva",
            "nate20Tatyana21"
        ),
        User(
            "kutuzova.t.a@inbox.ru",
            "Tatiana Kutuzova",
            "Tatiana-Kutuzova",
            "nate20Tatiana21"
        ),
        User(
            "yusoltseva@yandex.ru",
            "Yulia Usoltseva",
            "Yulia-Usoltseva",
            "nate20Yulia21"
        ),
        User(
            "irrusina76@mail.ru",
            "Irina Rusina",
            "Irina-Rusina",
            "nate20Irina21",
        ),
        User(
            "kharlamovatv@yandex.ru",
            "Tatiana Kharlamova",
            "Tatiana-Kharlamova",
            "nate20Tatiana21"
        ),
        User(
            "annagorkova@inbox.ru",
            "Anna Gorkova",
            "Anna-Gorkova",
            "nate20Anna21"
        ),
        User(
            "Nbochorishvili@gmail.com",
            "Natasha Bochorishvili",
            "Natasha-Bochorishvili",
            "nate20Natasha21"
        ),
        User(
            "marsl@list.ru",
            "Liliya Bykova",
            "Liliya-Bykova",
            "nate20Liliya21"
        ),
        User(
            "korytina.73@mail.ru",
            "Irina Korytina",
            "Irina-Korytina",
            "nate20Irina21"
        ),
        User(
            "Andreeva.tl2012@mail.ru",
            "Tatiana Andreeva",
            "Tatiana-Andreeva",
            "nate20Tatiana21"
        ),
        User(
            "zelenskaya@yahoo.com",
            "Larisa Zelenskaya",
            "Larisa -Zelenskaya",
            "nate20Larisa21"
        ),
        User(
            "Sneva@bk.ru",
            "Snezhana Voronina",
            "Snezhana-Voronina",
            "nate20Snezhana21"
        ),
        User(
            "gennett@rambler.ru",
            "Irina Bezyaeva",
            "Irina-Bezyaeva",
            "nate20Irina21"
        ),
        User(
            "erakit@yandex.ru",
            "Elena Rakityanskaya",
            "Elena-Rakityanskaya",
            "nate20Elena21"
        ),
        User(
            "goumovskayagaln@mail.ru",
            "Galina Gumovsaya",
            "Galina-Gumovsaya",
            "nate20Galina21"
        ),
        User(
            "i.v.abidor@gmail.com",
            "Irina Abidor",
            "Irina-Abidor",
            "nate20Irina21"
        ),
        User(
            "olga.studies@yandex.ru",
            "Olga Murashova",
            "Olga-Murashova",
            "nate20Olga21"
        ),
        User(
            "tamila.buslaeva@yahoo.com",
            "Tamila Buslaeva",
            "Tamila-Buslaeva",
            "nate20Tamila21"
        ),
        //new users
        User("v.shistcko@yandex.ru",
            "Viktoria Shistcko",
            "Viktoria-Shistcko",
            "nate20Viktoria21"
        ),
        User("leonrina22@gmail.com",
            "IRINA LEONTEVA",
            "IRINA-LEONTEVA",
            "nate20IRINA21"
        ),
        User("cordique@gmail.com",
            "Polina Kordik",
            "Polina-Kordik",
            "nate20Polina21"
        ),
        User("mary1800@list.ru",
            "Maria Khritina",
            "Maria-Khritina",
            "nate20Maria21"
        ),
        User("sj@ngs.ru",
            "Uliana Kshenovskaya",
            "Uliana-Kshenovskaya",
            "nate20Uliana21"
        ),
        User("krasovizkaya-yulia@yandex.ru",
            "Yulia Krasovizkaya",
            "Yulia-Krasovizkaya",
            "nate20Yulia21"
        ),
        User("likewoah.itsrhoda@gmail.com",
            "Rhoda Mirna Justin",
            "Rhoda-Mirna-Justin",
            "nate20Rhoda21"
        ),
        User("chernuhina.nastia@yandex.ru",
            "Anastasia Chernihina",
            "Anastasia-Chernihina",
            "nate20Anastasia21"
        ),
        User("kachina09@mail.ru",
            "Elena Rood",
            "Elena-Rood",
            "nate20Elena21"
        ),
        User("tkalenko@mail.ru",
            "Svetlana Tkalenko",
            "Svetlana-Tkalenko",
            "nate20Svetlana21"
        ),
        User("marstr@inbox.ru",
            "Marina Strelnikova",
            "Marina-Strelnikova",
            "nate20Marina21"
        ),
        User("peter_mitchell@mail.ru",
            "Dr Peter J Mitchell",
            "Peter -Mitchell",
            "nate20Peter 21"
        ),
        User("nadya.zhuravleva28@gmail.com",
            "Nadezhda Zhuravleva",
            "Nadezhda-Zhuravleva",
            "nate20Nadezhda21"
        ),
        User("lubov.okulova@gmail.com",
            "Liubov Okulova",
            "Liubov-Okulova",
            "nate20Liubov21"
        ),
        User("maevskiy-vm@rudn.ru",
            "Vladimir Maevskiy",
            "Vladimir-Maevskiy",
            "nate20Vladimir21"
        ),
        User("queenmargaret06@rambler.ru",
            "Margarita Pylaeva",
            "Margarita-Pylaeva",
            "nate20Margarita21"
        ),
        User("NatalieMelch@mail.ru",
            "Natalia Melchenkova",
            "Natalia-Melchenkova",
            "nate20Natalia21"
        ),
        User("tatiana6666@mail.ru",
            "Tatiana Zaykova",
            "Tatiana-Zaykova",
            "nate20Tatiana21"
        ),
        User("chuchchy26@gmail.com",
            "Olga Shvedenko",
            "Olga-Shvedenko",
            "nate20Olga21"
        ),
        User("trelana40@list.ru",
            "Alexander N. Tretjukhin",
            "Alexander -Tretjukhin",
            "nate20Alexander 21"
        ),
        User("bav46@mail.ru",
            "Angelina Bezrukova",
            "Angelina-Bezrukova",
            "nate20Angelina21"
        ),
        User("kback@cambridge.org",
            "Katie Back",
            "Katie-Back",
            "nate20Katie21"
        ),
        User("Sobolevskayangelina@gmail.com",
            "Angelina Lukashevich",
            "Angelina-Lukashevich",
            "nate20Angelina21"
        ),
        User("bureninanv@mail.ru",
            "Natalia Burenina",
            "Natalia-Burenina",
            "nate20Natalia21"
        ),
        User("Alina.en.es.it@gmail.com",
            "Alina Kartseva",
            "Alina-Kartseva",
            "nate20Alina21"
        ),
        User("elapshina96@gmail.com",
            "Ekaterina Timofeeva",
            "Ekaterina-Timofeeva",
            "nate20Ekaterina21"
        ),
        User("Irene_shvidko@mail.ru",
            "Irene Shvidko",
            "Irene-Shvidko",
            "nate20Irene21"
        ),
        User("sokolov.kol@gmail.com",
            "Nickolay Sokolov",
            "Nickolay-Sokolov",
            "nate20Nickolay21"
        ),
        User("kinset96@hotmail.com",
            "Irina Titarenko",
            "Irina-Titarenko",
            "nate20Irina21"
        ),
        User("svetlanaushakova@mail.ru",
            "Svetlana Ushakova",
            "Svetlana-Ushakova",
            "nate20Svetlana21"
        ),
        User("tereshchenko_inn@mail.ru",
            "Inna Tereshchenko",
            "Inna-Tereshchenko",
            "nate20Inna21"
        ),
        User("0503266112@mail.ru",
            "Olga Glushchenko",
            "Olga-Glushchenko",
            "nate20Olga21"
        ),
        User("dashavas2904@rambler.ru",
            "Darya Vasilenko",
            "Darya-Vasilenko",
            "nate20Darya21"
        ),
        User("hibbslouise@gmail.com",
            "Dilara Louise Hibbs",
            "Dilara-Louise-Hibbs",
            "nate20Dilara-Louise21"
        ),
        User("anamoran.islas@gmail.com",
            "Moran Islas Ana Eugenia",
            "Ana-Eugenia-Moran-Islas",
            "nate20Ana-Eugenia21"
        ),
        User("filimonenkola@gmail.com",
            "Larisa Filimonenko",
            "Larisa-Filimonenko",
            "nate20Larisa21"
        ),
        User("alenaboiko9@yandex.ru",
            "Elena Boiko",
            "Elena-Boiko",
            "nate20Elena21"
        ),
        User("shmaraeva@gmail.com",
            "Anastasia Shmaraeva",
            "Anastasia-Shmaraeva",
            "nate20Anastasia21"
        ),
        User("kbalanenko@mail.ru",
            "Ksenia Balanenko",
            "Ksenia-Balanenko",
            "nate20Ksenia21"
        ),
        User("Tati.odintsova@yandex.ru",
            "Tatiana Odintsova",
            "Tatiana-Odintsova",
            "nate20Tatiana21"
        ),
        User("svetamasterskikh@mail.ru",
            "Svetlana Shvab",
            "Svetlana-Shvab",
            "nate20Svetlana21"
        ),
        User("michaelilian93@gmail.com",
            "Mikhail Ilin",
            "Mikhail-Ilin",
            "nate20Mikhail21"
        ),
        User("soa27@mail.ru",
            "Olga Simonova",
            "Olga-Simonova",
            "nate20Olga21"
        ),
        User("Chistyakova.vera2@gmail.com",
            "Vera Chistyakova",
            "Vera-Chistyakova",
            "nate20Vera21"
        ),
        User("natalie_ivleva@mail.ru",
            "Natalia Kuznetsova",
            "Natalia-Kuznetsova",
            "nate20Natalia21"
        ),
        User("gl2010@yandex.ru",
            "Lubov Eliseeva",
            "Lubov-Eliseeva",
            "nate20Lubov21"
        ),
        User("aleksandra-orlova-95@mail.ru",
            "Alexandra Orlova",
            "Alexandra-Orlova",
            "nate20Alexandra21"
        ),
        User("floop@floop.dev",
            "Alexander Babichev",
            "Alexander-Babichev",
            "nate20Alexander21"
        ),
        User("juliazarubina.91@gmail.com",
            "Yuliya Pushkina",
            "Yuliya-Pushkina",
            "nate20Yuliya21"
        ),
        User("atretyakova1994@gmail.com",
            "Anastasia Tretyakova",
            "Anastasia-Tretyakova",
            "nate20Anastasia21"
        ),
        User("alice.kostenkova@mail.ru",
        "Alice Kostenkova",
        "ST-Alice",
        "nate20Alice21"
        ),
        User("yana.yana-subbotina2017@yandex.ru",
        "Subbotina Yana",
        "ST-Subbotina",
        "nate20Subbotina21"
        ),
        User("fow_yo@mail.ru",
        "Mayya Tihonova",
        "ST-Mayya",
        "nate20Mayya21"
        ),
        User("m1802450@edu.misis.ru",
        "Nikoleta Mandish",
        "ST-Nikoleta",
        "nate20Nikoleta21"
        ),
        User("polya.puziryova@yandex.ru",
        "Polina Puzyreva",
        "ST-Polina",
        "nate20Polina21"
        ),
        User("m1805092@edu.misis.ru",
        "Naumenko Danil",
        "ST-Naumenko",
        "nate20Naumenko21"
        ),
        User("karina.karpova.2000@mail.ru",
        "Karpov Korina",
        "ST-Karpov",
        "nate20Karpov21"
        ),
        User("medalis@bk.ru",
        "Barilko Mariya",
        "ST-Barilko",
        "nate20Barilko21"
        ),
        User("katya.sar00@mail.ru",
        "Katya Sarbatova",
        "ST-Katya",
        "nate20Katya21"
        ),
        User("ninulcha111@gmail.com",
        "Nina",
        "ST-Nina",
        "nate20Nina21"
        ),
        User("Smirnova.polina1999@gmail.com",
        "Polina",
        "ST-Polina",
        "nate20Polina21"
        ),
        User("ms.sobelnikova@mail.ru",
        "Sobelnikova Anastasia",
        "ST-Sobelnikova",
        "nate20Sobelnikova21"
        ),
        User("ladadomova@mail.ru",
        "Lada Domova",
        "ST-Lada",
        "nate20Lada21"
        ),
        User("vernigor1999@gmail.com",
        "Vernigor Anastasia",
        "ST-Vernigor",
        "nate20Vernigor21"
        ),
        User("lena.firsova.00@inbox.ru",
        "Elena",
        "ST-Elena",
        "nate20Elena21"
        ),
        User("liza.savochkina2@gmail.com",
        "Elizaveta Savochkina",
        "ST-Elizaveta",
        "nate20Elizaveta21"
        ),
        User("natashasharikova1@mail.ru",
        "Natalia Gennadyevna",
        "ST-Natalia",
        "nate20Natalia21"
        ),
        User("apostnikova22@gmail.com",
        "Alexandra Postnikova",
        "ST-Alexandra",
        "nate20Alexandra21"
        ),
        User("lizasamsonova28@gmail.com",
        "Samsonova Elizaveta",
        "ST-Samsonova",
        "nate20Samsonova21"
        ),
        User("alisamanuylova@mail.ru",
        "Alice Manuylova",
        "ST-Alice1",
        "nate20Alice21"
        ),
        User("kosareva.el.al@gmail.com",
        "Kosareva Elizaveta",
        "ST-Kosareva",
        "nate20Kosareva21"
        ),
        User("sashatabunchik@gmail.com",
        "Alexandra Tabunchik",
        "ST-Alexandra",
        "nate20Alexandra21"
        ),
        User("kidding.fish@gmail.com",
        "Alina Podluzhnaya",
        "ST-Alina",
        "nate20Alina21"
        ),
        User("linaponomareva2201@mail.ru",
        "Angelina",
        "ST-Angelina",
        "nate20Angelina21"
        ),
        User("el.chamata@yandex.ru",
        "Elena Chamata",
        "ST-Elena1",
        "nate20Elena21"
        ),
        User("koneva.stella@mail.ru",
        "Stella Koneva",
        "ST-Stella",
        "nate20Stella21"
        ),
        User("nikol1153@gmail.com",
        "Nikolai Marchenko",
        "ST-Nikolai",
        "nate20Nikolai21"
        ),
        User("angelzakirova1@gmail.com",
        "Angelica Zakirova",
        "ST-Angelica",
        "nate20Angelica21"
        ),
        User("rinazanova@gmail.com",
        "Arina Magomedova",
        "ST-Arina",
        "nate20Arina21"
        ),
        User("shilovtseva.s.a@gmail.com",
        "Sofia Shilovtseva",
        "ST-Sofia",
        "nate20Sofia21"
        ),
        User("iraklimenteshashvili2@gmail.com",
        "Irakli Menteshashvili",
        "ST-Irakli",
        "nate20Irakli21"
        ),
        User("moikoiheh@gmail.com",
        "Alina Shin",
        "ST-Alina2",
        "nate20Alina21"
        ),
        User("tikhontseva.maria@gmail.com",
        "Maria Tikhontseva",
        "ST-Maria",
        "nate20Maria21"
        ),
        User("xodakak@gmail.com",
        "Khodakovskaya Daria",
        "ST-Khodakovskaya",
        "nate20Khodakovskaya21"
        ),
        User("m1900620@edu.misis.ru",
        "Daniel Gimadeev",
        "ST-Daniel",
        "nate20Daniel21"
        ),
        User("m1904263@edu.misis.ru",
        "Kraschenko Kira",
        "ST-Kraschenko",
        "nate20Kraschenko21"
        ),
        User("nnssh22@bk.ru",
        "Anna Smurygina",
        "ST-Anna",
        "nate20Anna21"
        ),
        User("alevia@list.ru",
        "Levi Alyona",
        "ST-Levi",
        "nate20Levi21"
        ),
        User("sofya1251@yandex.ru",
        "Sofia Mesiatseva",
        "ST-Sofia",
            "nate20Sofia21"
        ),
        User("paulleena@gmail.com",
        "Alina Makarova",
        "ST-Alina3",
        "nate20Alina21"
        ),
        User("varvara.zhuravleva.2017@mail.ru",
        "Zhuravleva Varvara",
        "ST-Zhuravleva",
        "nate20Zhuravleva21"
        ),
        User("velgsokayaliz@yandex.ru",
        "Afonicheva Elizaveta",
        "ST-Afonicheva",
        "nate20Afonicheva21"
        ),
        User("m1900519@edu.misis.ru",
        "Semina Ksenia",
        "ST-Semina",
        "nate20Semina21"
        ),
        User("mariklary@icloud.com",
        "Zaitseva Maria",
        "ST-Zaitseva",
        "nate20Zaitseva21"
        ),
        User("tanchin.karina@mail.ru",
        "Karina Tanchin",
        "ST-Karina",
        "nate20Karina21"
        ),
        User("milana.petrova.00@inbox.ru",
        "Milana Petrova",
        "ST-Milana",
        "nate20Milana21"
        ),
        User("m1907419@edu.misis.ru",
        "Polyakova Ksenia",
        "ST-Polyakova",
        "nate20Polyakova21"
        ),
        User("katyas729@mail.ru",
        "Semenova Ekaterina",
        "ST-Semenova",
        "nate20Semenova21"
        ),
        User("m1909216@edu.misis.ru",
        "Lopushova Valeria",
        "ST-Lopushova",
        "nate20Lopushova21"
        ),
        User("danisa0127@gmail.com",
        "Daria Sokolova",
        "ST-Daria",
        "nate20Daria21"
        ),
        User("Kalinina.e.u@yandex.ru",
        "Ekaterina Kalinina",
        "ST-Ekaterina",
        "nate20Ekaterina21"
        ),
        User("irina.koronnova@yandex.ru",
        "Koronnova Irene",
        "ST-Koronnova",
        "nate20Koronnova21"
        ),
        User("Manannikova.nas@yandex.ru",
        "Anastasia Manannikova",
        "ST-Anastasia",
        "nate20Anastasia21"
        ),
        User("tatyana.malysheva.02@mail.ru",
        "Tatyana Malysheva",
        "ST-Tatyana",
        "nate20Tatyana21"
        ),
        User("j_igonina@bk.ru",
        "Yulianna Igonina",
        "ST-Yulianna",
        "nate20Yulianna21"
        ),
        User("fardievaa@yandex.ru",
        "Alina Fardieva",
        "ST-Alina4",
        "nate20Alina21"
        ),
        User("fabic27122001@inbox.ru",
        "Belyaev Ilya",
        "ST-Belyaev",
        "nate20Belyaev21"
        ),
        User("vegovskaya@mail.ru",
        "Vigovskaya Daria",
        "ST-Vigovskaya",
        "nate20Vigovskaya21"
        ),
        User("m1901349@edu.misis.ru",
        "Nadezhda Shevchenko",
        "ST-Nadezhda",
        "nate20Nadezhda21"
        ),
        User("maryloufonikon@gmail.com",
            "Shumakov Maxim",
            "ST-Shumakov",
            "nate20Shumakov21"
        ),
        User("nazhestkina.daria@yandex.ru",
            "Nazhestkina Daria",
            "ST-Nazhestkina",
            "nate20Nazhestkina21"
        ),
        User("staroselec333@gmail.com",
            "Maria Staroselets",
            "ST-Maria1",
            "nate20Maria21"
        ),
        User("pnkkatia@mail.ru",
            "Ekaterina Pankratieva",
            "ST-Ekaterina1",
            "nate20Ekaterina21"
        ),
        User("sekachevakoka@gmail.com",
            "Christina Sekacheva",
            "ST-Christina",
            "nate20Christina21"
        ),
        User("m1901862@edu.misis.ru",
            "Eremina Ekaterina",
            "ST-Eremina",
            "nate20Eremina21"
        ),
        User("vlada.winter@yandex.ru",
            "Vladislava Zimina",
            "ST-Vladislava",
            "nate20Vladislava21"
        ),
        User("elizaveta0116@mail.ru",
            "Popova Elizaveta",
            "ST-Popova",
            "nate20Popova21"
        ),
        User("kiriana.zen@mail.ru",
            "Kiriana Zenkevich",
            "ST-Kiriana",
            "nate20Kiriana21"
        ),
        User("viktoriaguseva2002@gmail.com",
            "Viktoria Guseva",
            "ST-Viktoria",
            "nate20Viktoria21"
        ),
        User("waygoer@mail.ru",
            "Christine Pimenova",
            "ST-Christine",
            "nate20Christine21"
        ),
        User("anna.ibr000@yandex.ru",
            "Ibryashkina Anna",
            "ST-Ibryashkina",
            "nate20Ibryashkina21"
        ),
        User("irina2001108@gmail.com",
            "Chernova Irina",
            "ST-Chernova",
            "nate20Chernova21"
        ),
        User("bulycheva-2015@inbox.ru",
            "Elizaveta Bulycheva",
            "ST-Elizaveta1",
            "nate20Elizaveta21"
        ),
        User("m1901451@edu.misis.ru",
            "Valeriya Vodyanova",
            "ST-Valeriya",
            "nate20Valeriya21"
        ),
        User("yahoo654@mail.ru",
            "Husnetdinova Venera",
            "ST-Husnetdinova",
            "nate20Husnetdinova21"
        ),
        User("nana08112001@mail.ru",
            "Anastasia",
            "ST-Anastasia1",
            "nate20Anastasia21"
        ),
        User("svkutina@yandex.ru",
            "Svetlana Kutina",
            "ST-Svetlana",
            "nate20Svetlana21"
        ),
        User("spegova@gmail.com",
            "Aleksandra Pegova",
            "ST-Aleksandra",
            "nate20Aleksandra21"
        ),
        User("wutmn2001@gmail.com",
            "Ekaterina Kharchenko",
            "ST-Ekaterina2",
            "nate20Ekaterina21"
        ),
        User("semyon20012002@gmail.com",
            "Sidorov Andrew",
            "ST-Sidorov",
            "nate20Sidorov21"
        ),
        User("maksmailbox@mail.ru",
            "Maxim Alexandrov",
            "ST-Maxim",
            "nate20Maxim21"
        ),
        User("Trofimycheva2001@mail.ru",
            "Anastasia Alekseevna",
            "ST-Anastasia2",
            "nate20Anastasia21"
        ),
        User("ann_dunaeva@mail.ru",
            "Anna Dunaeva",
            "ST-Anna1",
            "nate20Anna21"
        ),
        User("sansmart@mail.ru",
            "Selishchev Maksim",
            "ST-Selishchev",
            "nate20Selishchev21"
        ),
        User("kristina_doctorova2306@mail.ru",
            "Christine Doctorova",
            "ST-Christine1",
            "nate20Christine21"
        ),
        User("leranikol.2012@mail.ru",
            "Nikolaeva Valeria",
            "ST-Nikolaeva",
            "nate20Nikolaeva21"
        ),
        User("m1906263@edu.misis.ru",
            "Varavva Maria",
            "ST-Varavva",
            "nate20Varavva21"
        ),
        User("iamenotnasty@gmail.com",
            "Anastasia Shlyk",
            "ST-Anastasia3",
            "nate20Anastasia21"
        ),
        User("e.protchenko@gmail.com",
            "Protchenko Evdokia",
            "ST-Protchenko",
            "nate20Protchenko21"
        ),
        User("kraynova.darya.00@mail.ru",
            "Darya",
            "ST-Darya",
            "nate20Darya21"
        ),
        User("anastayms@gmail.com",
            "Volkova Anastasia",
            "ST-Volkova",
            "nate20Volkova21"
        ),
        User("dasha_bondareva_01@bk.ru",
            "Bondareva Darya",
            "ST-Bondareva",
            "nate20Bondareva21"
        ),
        User("m1nai4@mail.ru",
            "Nikita Minaev",
            "ST-Nikita",
            "nate20Nikita21"
        ),
        User("ksk-dn-16@ya.ru",
            "Denis Lobanov",
            "ST-Denis",
            "nate20Denis21"
        ),
        User("julyana052@gmail.com",
            "Ulyana Deeva",
            "ST-Ulyana",
            "nate20Ulyana21"
        ),
        User("tatiana.ad.sirotina@gmail.com",
            "Sirotina Tatiana",
            "ST-Sirotina",
            "nate20Sirotina21"
        ),
        User("m1903408@edu.misis.ru",
            "Kiseleva Anna",
            "ST-Kiseleva",
            "nate20Kiseleva21"
        ),
        User("carpectem@gmail.com",
            "Nadson Ekaterina",
            "ST-Nadson",
            "nate20Nadson21"
        ),
        User("parfenovary@gmail.com",
            "Parfenova Maria",
            "ST-Parfenova",
            "nate20Parfenova21"
        ),
        User("ktmodest@gmail.com",
            "Prohorova Ekaterina",
            "ST-Prohorova",
            "nate20Prohorova21"
        ),
        User("cova-letto@yandex.ru",
            "Dayana Saibnazarova",
            "ST-Dayana",
            "nate20Dayana21"
        ),
        User("m1904798@edu.misis.ru",
            "Amina Bersanova",
            "ST-Amina",
            "nate20Amina21"
        ),
        User("nastya.parfenova01@mail.ru",
            "Anastasia",
            "ST-Anastasia4",
            "nate20Anastasia21"
        ),
        User("alexandrakhlop@gmail.com",
            "Alex",
            "ST-Alex",
            "nate20Alex21"
        ),
        User("nikulya.kim.00@mail.ru",
            "Kim Khyon Mi",
            "ST-Kim Khyon",
            "nate20Kim Khyon21"
        ),
        User("m1911534@edu.misis.ru",
            "Elina Agaeva",
            "ST-Elina",
            "nate20Elina21"
        ),
        User("anastasiya.klopyzhnikova@mail.ru",
            "Anastasiya Klopyzhnikova",
            "ST-Anastasiya",
            "nate20Anastasiya21"
        ),
        User("Odinokova.didi@yandex.ru",
            "Diana Odinokova",
            "ST-Diana",
            "nate20Diana21"
        ),
        User("abelikova@rttv.ru",
            "Anna Belikova",
            "ST-Anna2",
            "nate20Anna21"
        ),
        User("avdarii@mail.ru",
            "Daria",
            "ST-Daria1",
            "nate20Daria21"
        ),
        User("alena2324082000@gmail.com",
            "Alyona",
            "ST-Alyona",
            "nate20Alyona21"
        ),
        User("m1801569@edu.misis.ru",
            "Vladimir Ershov",
            "ST-Vladimir",
            "nate20Vladimir21"
        ),
        User("sasha.d.01@list.ru",
            "Alexandra",
            "ST-Alexandra",
            "nate20Alexandra21"
        ),
        User("polifed29@gmail.com",
            "Polina Fedorova",
            "ST-Polina",
            "nate20Polina21"
        ),
        User("shumaevaolga18@gmail.com",
            "Olga",
            "ST-Olga",
            "nate20Olga21"
        ),
        User("m2002084@edu.misis.ru",
            "Tatiana Anatolyevna Cherkasova",
            "Tatiana-Cherkasova",
            "nate20Tatiana21"
        ),
        User("melehina.elena@list.ru",
            "Elena Melekhina",
            "Elena-Melekhina",
            "nate20Elena21"
        ),
        User("ponomareva201@yandex.com",
            "Anna Saprykina",
            "Anna-Saprykina",
            "nate20Anna21"
        ),
        User("ivolga777@mail.ru",
            "Olga Khudyakova",
            "Olga-Khudyakova",
            "nate20Olga21"
        ),
        User("olganikif@mail.ru",
            "Olga Aleshchenko",
            "Olga-Aleshchenko",
            "nate20Olga21"
        ),
        User("nasti_1133@mail.ru",
            "Anastasia Grineva",
            "Anastasia-Grineva",
            "nate20Anastasia21"
        ),
        User(
            "klimenko478@yandex.ru",
            "Ekaterina Klimenko",
            "Ekaterina",
            "nate20Ekaterina21",
        ),
        User(
            "olga.vavelyuk@gmail.com",
            "Olga Vavelyuk",
            "Olga-Vavelyuk",
            "nate20Olga21"
        ),
        User(
            "svilyina@mail.ru",
            "Svetlana Ilina",
            "Svetlana-Ilina",
            "nate20Svetlana21"
        ),
        User(
            "lilia.spbguide@gmail.com",
            "Liliya Mazurkevich",
            "Liliya-Mazurkevich",
            "nate20Liliya21"
        ),
        User(
            "orlovakult@gmail.com",
            "Elizaveta Orlova",
            "Elizaveta-Orlova",
            "nate20Elizaveta21"
        ),
        User(
            "popova-msu@yandex.ru",
            "Anna Popova",
            "Anna-Popova",
            "nate20Anna21"
        ),
        User(
            "julie.stolyarchuk@gmail.com",
            "Julie Stolyarchuk",
            "Julie-Stolyarchuk",
            "nate20Julie21"
        ),
        User(
            "olbelyaeva15@gmail.com",
            "Olga Belyayeva",
            "Olga-Belyayeva",
            "nate20Olga21"
        ),
        User(
            "magrabovskaya@gmail.com",
            "Maria Grabovskaya",
            "Maria-Grabovskaya",
            "nate20Maria21"
        ),
        User(
            "suhareva-tatyana@mail.ru",
            "Tatiana Sukhareva",
            "Tatiana-Sukhareva",
            "nate20Tatiana21"
        ),
        User(
            "anagur7@mail.ru",
            "Anastasiya Gureeva",
            "Anastasiya-Gureeva",
            "nate20Anastasiya21"
        ),
        User(
            "snch0303@mail.ru",
            "Svetlana Chernikova",
            "Svetlana-Chernikova",
            "nate20Svetlana21"
        ),
        User(
            "dashapetrakova@gmail.com",
            "Daria Petrakova",
            "Daria-Petrakova",
            "nate20Daria21"
        ),
        User(
            "kupruk@yandex.ru",
            "Oksana Kupruk",
            "Oksana-Kupruk",
            "nate20Oksana21"
        ),
        User(
            "saida.svetlana@gmail.com",
            "Svetlana Saida",
            "Svetlana-Saida",
            "nate20Svetlana21"
        ),
        User(
            "krykova_irina@mail.ru",
            "Irina Leonova",
            "Irina-Leonova",
            "nate20Irina21"
        ),
        User(
            "olga_kl@inbox.ru",
            "Olga Kladieva",
            "Olga-Kladieva",
            "nate20Olga21"
        ),
        User(
            "anton.kolesnikg@gmail.com",
            "Anton Kolesnik",
            "Anton-Kolesnik",
            "nate20Anton21"
        ),
        User(
            "zaraphshan@mail.ru",
            "Anastasia Tutova",
            "Anastasia-Tutova",
            "nate20Anastasia21"
        ),
        User(
            "bnv_75@mail.ru",
            "Natalia Belozertseva",
            "Natalia-Belozertseva",
            "nate20Natalia21"
        ),
        User(
            "sweetkalina@mail.ru",
            "Tatyana Kalinina",
            "Tatyana-Kalinina",
            "nate20Tatyana21"
        ),
        User(
            "naskosareva@mail.ru",
            "Anastasia Kosareva",
            "Anastasia-Kosareva",
            "nate20Anastasia21"
        ),
        User(
            "aliabbas9292sy@gmail.com",
            "Ali Abbas",
            "Ali-Abbas",
            "nate20Ali21"
        ),
        User(
            "bogatikova@gmail.com",
            "Julia Bogatikova",
            "Julia-Bogatikova",
            "nate20Julia21"
        ),
        User(
            "graschenkova.gn@misis.ru",
            "Galina Grashchenkova",
            "Galina-Grashchenkova",
            "nate20Galina21"
        ),
        User(
            "sogu@mail.ru",
            "Elza Arutyunova-Yastrebkova",
            "Elza-Arutyunova-Yastrebkova",
            "nate20Elza21"
        ),
        User(
            "alx.artamonov@gmail.com",
            "Alexander Artamonov",
            "Alexander-Artamonov",
            "nate20Alexander21"
        ),
        User(
            "anastasiayats@gmail.com",
            "Anastasia Iatsenko",
            "Anastasia-Iatsenko",
            "nate20Anastasia21"
        ),
        User(
            "rbv996@gmail.com",
            "Evgeniya Kim",
            "Evgeniya-Kim",
            "nate20Evgeniya21"
        ),
        User(
            "turkevich.ekat@gmail.com",
            "Yekaterina Turkevich",
            "Yekaterina-Turkevich",
            "nate20Yekaterina21"
        ),
        User(
            "ru_ni@mail.ru",
            "Irina Zemlyanukhina",
            "Irina-Zemlyanukhina",
            "nate20Irina21"
        ),
        User("dplaksin.work@gmail.com",
            "Denis Plaksin",
            "Denis-Plaksin",
            "nate20Denis21"
        ),
        User("i.krykova@misis.ru",
            "Irina Leonova",
            "Irina-Leonova",
            "nate20Irina21"
        ),
        User("irinareg7@gmail.com",
            "Irina Zabavina",
            "Irina-Zabavina",
            "nate20Irina21"
        ),
        User("int4041026@yandex.ru",
            "Roman Esipov",
            "Roman-Esipov",
            "nate20Roman21"
        ),
        User("gaerlachsenior@gmail.com",
            "Olga Kulikova",
            "Olga-Kulikova",
            "nate20Olga21"
        ),
        User("svetabelous@yahoo.com",
            "Svetlana Belous",
            "Svetlana-Belous",
            "nate20Svetlana21"
        ),
        User("liliabondareva@gmail.com",
            "Lilia Bondareva",
            "Lilia-Bondareva",
            "nate20Lilia21"
        ),
        User("e.shchaveleva@gmail.com",
            "Ekaterina Shchaveleva",
            "Ekaterina-Shchaveleva",
            "nate20Ekaterina21"
        ),
        User("polina540@yandex.ru",
            "Karisheva Polina",
            "ST-Karisheva",
            "nate20Karisheva21"
        ),
        User("m1906167@edu.misis.ru",
            "Parfenova Anastasia",
            "ST-Parfenova",
            "nate20Parfenova21"
        ),
        User("eostankovich@gmail.com",
            "Ekaterina Ostankovich",
            "ST-Ekaterina3",
            "nate20Ekaterina21"
        ),
        User("ya_yanus@mail.ru",
            "Pryazhentseva Yana",
            "ST-Pryazhentseva",
            "nate20Pryazhentseva21"
        ),
        User("Yarprost@gmail.com",
            "Yaroslav Prostakov",
            "ST-Yaroslav",
            "nate20Yaroslav21"
        ),
        User("Tim.cheplakov@gmail.com",
            "Timur Cheplakov",
            "ST-Timur",
            "nate20Timur21"
        ),
        User("anastasiaverina1@gmail.com",
            "Averina Anastasia",
            "ST-Averina",
            "nate20Averina21"
        ),
        User("sanyagaraeva@mail.ru",
            "Garaeva Alexandra",
            "ST-Garaeva",
            "nate20Garaeva21"
        ),
        User("milanasopova10@gmail.com",
            "Milana Sopova",
            "ST-Milana",
            "nate20Milana21"
        ),
        User("kseniadementy@gmail.com",
            "Dementyeva Ksenia",
            "ST-Dementyeva",
            "nate20Dementyeva21"
        ),
        User("Ialmasyan@mail.ru",
            "Almasyan Irina",
            "ST-Almasyan",
            "nate20Almasyan21"
        ),
        User("1029805282@qq.com",
            "Sun Daoying",
            "ST-Sun",
            "nate20Sun21"
        ),
        User("mayya_porchelli@mail.ru",
            "Porcelli Maya",
            "ST-Porcelli",
            "nate20Porcelli21"
        ),
        User("alinadranyaeva@gmail.com",
            "Alina Dranyaeva",
            "ST-Alina5",
            "nate20Alina21"
        ),
        User("www.okno-v-evropu@mail.ru",
            "Kristina Solovyova",
            "ST-Kristina",
            "nate20Kristina21"
        ),
        User("darinashakirova@yandex.ru",
            "Shakirova Darina",
            "ST-Shakirova",
            "nate20Shakirova21"
        ),
        User("dima.kart99@gmail.com",
            "Sukiasyan Dmitry",
            "ST-Sukiasyan",
            "nate20Sukiasyan21"
        ),
        User("dnsrlzrd@gmail.com",
            "Daria Pluzhnikova",
            "ST-Daria2",
            "nate20Daria21"
        ),
        User("anastasiiae2000@mail.ru",
            "Anastasia",
            "ST-Anastasia5",
            "nate20Anastasia21"
        ),
        User("vikaa.kulagina@yandex.ru",
            "Kulagina Viktoria",
            "ST-Kulagina",
            "nate20Kulagina21"
        ),
        User("felicita-all@yandex.ru",
            "Alexandra Tarakanova",
            "ST-Alexandra",
            "nate20Alexandra21")
    )
    for (user in users)
        addNewUser(user)
}