package com.example.applicationforconferencemisis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applicationforconferencemisis.Data.Firebase.NODE_CONFERENCES
import com.example.applicationforconferencemisis.Data.Firebase.REF_DATABASE_ROOT
import com.example.applicationforconferencemisis.Data.Firebase.initFirebase
import com.example.applicationforconferencemisis.Data.Models.Conference
import com.example.applicationforconferencemisis.Data.Models.MainSchedule
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun makeToast(context: Context, arg: String) {
    Toast.makeText(context, arg, Toast.LENGTH_LONG).show()
}

fun Fragment.replaceFragment(fragment: Fragment){
    /* Функция расширения для Fragment, позволяет устанавливать фрагменты */
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.containerForFrag,
            fragment
        )?.commit()
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}

fun ImageView.downloadAndSetImage(url:String){
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_profile)
        .into(this)
}

fun setMainSchedule(){
    val arrayConference3 = arrayListOf<MainSchedule>(
        MainSchedule("Registration for Workshops","8:30-8:40"),
        MainSchedule("Workshops (see detailed schedule)","8:40-9:25"),
        MainSchedule("Registration for Workshops","9:25-9:35"),
        MainSchedule("Workshops (see detailed schedule)","9:35-10:20"),
        MainSchedule("Break","10:20-10:30"),
        MainSchedule("Conference Opening: Svetlana G. Ter-Minasova\n" +
                "Opening Plenary Talks\n" +
                "Peter Watkins\n" +
                "Reading Evolution: Helping Learners to Read Online\n" +
                "Kelley Calvert\n" +
                "Maintaining Motivation and Preventing Teacher Burnout with Student-сentered Instruction","10:30-12:30"),
        MainSchedule("Lunch","12:30-13:30"),
        MainSchedule("Concurrent Sessions (see detailed schedule)","13.30-15.00"),
        MainSchedule("Break","15.00-15.15"),
        MainSchedule("Concurrent Sessions (see detailed schedule)","15.15-16.45"),
        MainSchedule("Break","16:45-17:00"),
        MainSchedule("Touchstone@MISIS English Language Program: 10th Anniversary Round-table","17:00-18:00"),
    )

    initFirebase()
    for (i in 0..arrayConference3.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Schedule").child(i.toString()).setValue(arrayConference3[i])
    }
    val arrayConference4 = arrayListOf<MainSchedule>(
        MainSchedule("Registration for Workshops","8:30-8:40"),
        MainSchedule("Workshops (see detailed schedule)","8:40-9:25"),
        MainSchedule("Registration for Workshops","9:25-9:35"),
        MainSchedule("Workshops (see detailed schedule)","9:35-10:20"),
        MainSchedule("Break","10:20-10:30"),
        MainSchedule("Plenary Tech Talks:\n" +
                "Susana Anton (Cambridge University Press),\n" +
                "Alexey Konobeev, Mikhail Sverdlov (Skyeng),\n" +
                "Julia Khukalenko (Far Eastern Federal University),\n" +
                "Maria Anikina (Uchi.ru)","10:30-12:30"),
        MainSchedule("Lunch","12:30-13:30"),
        MainSchedule("Concurrent Sessions (see detailed schedule)","13.30-15.00"),
        MainSchedule("Break","15.00-15.15"),
        MainSchedule("Concurrent Sessions (see detailed schedule)","15.15-16.45"),
        MainSchedule("Break","16:45-17:00"),
        MainSchedule("Young Voices:\n" +
                "Q&A with David Crystal","17:00-18:00"),
    )

    for (i in 0..arrayConference4.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Schedule").child(i.toString()).setValue(arrayConference4[i])
    }
    val arrayConference5 = arrayListOf<MainSchedule>(
        MainSchedule("Young Voices Section","09:30-12:30"),
        MainSchedule("Elena Solovova Honorary Readings (in Russian)\n" +
                "\n" +
                "Kuzmina L.G.\n" +
                "E.N. Solovova and the Russian methodology of ELT\n" +
                "Markova E.S.\n" +
                "E.N. Solovova and the practice of ELT","11:00-12:30"),
        MainSchedule("Break","12:30-13:00"),
        MainSchedule("Closing Plenary Talk\n" +
                "Maria V. Verbitskaya\n" +
                "Prospective Model of the Russian National Examination in Foreign Languages (EGE) and the 21st Century Skills (in Russian, interpreting provided)","13:00-14:00"),
    )

    for (i in 0..arrayConference5.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 5").child("Schedule").child(i.toString()).setValue(arrayConference5[i])
    }
}

fun setConferencesWorkshop(){
    val arrayConferencesWorkshop3 = arrayListOf<Conference>(
        Conference("1","Digital Methodology","Classroom management: online vs offline.","8:45 - 9:30","Peresada Elena"),
        Conference("2","New Frontiers of Testing and Assessment","Energise, Optimise. Digitalise.","8:45 - 9:30","Kovaleva Yelena, Mogunova Elena, Petrova Natalia"),
        Conference("3","Teacher Development","Developing English speaking and writing skills of B1-C1 students by means of Instagram.","8:45 - 9:30","Kalintsev Alexey"),
        Conference("4","Digital Methodology","Running teacher training online.","9:35-10:20","Brooks Simon"),
        Conference("5","Digital Methodology","Teaching speaking and critical thinking skills in a low-tech online environment: approaches, tasks, online instruments.","9:35-10:20","Titova Svetlana "),
        Conference("6","Teacher Development","Collaborative design tools in learning.","9:35-10:20","Bauters Merja"),
        Conference("7","Teacher Development","Building Reflexes in a Foreign Language.","9:35-10:20","Marcq Antoine"),
        Conference("8","Teacher Development","It is while writing, that we are thinking most.","9:35-10:20","Ivanova Tatiana"),
        Conference("9","Learner Diversity and Inclusion","International career planning: preparing students for the global workplace.","9:35-10:20","Mitchell Peter"),
        Conference("10","English Language Research","Acquiring talent & improving performance through AI powered English testing.","13:30-14:15","Pandey Namita"),
        Conference("11","Digital Methodology","Teaching pronunciation digitally.","14:15-15:00","Gordyshevskaya Polina"),
        Conference("12","English Language Research","Innovation, collaboration, transformation: education for a connected world.","15:00-15:45","Cowin Jasmin"),
        Conference("13","Teacher Development","SFL genre pedagogy: teaching language for its function in the genres of writing.","16:00-16:45","Brisk Maria"),
    )

    initFirebase()
    for (i in 0..arrayConferencesWorkshop3.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Workshop").child(i.toString()).setValue(arrayConferencesWorkshop3[i])
    }

    val arrayConferenceWorkshop4 = arrayListOf<Conference>(
        Conference("1","Digital Methodology","EFLtalks - teachers teaching teachers.","8:45 - 9:30","Howard Rob"),
        Conference("2","Teacher Development","Integrating critical thinking instruction into the Unified National Exam training programme.","8:45 - 9:30","Kapturova Evgenia"),
        Conference("3","New Frontiers of Testing and Assessment","Mind(full)ness: keep calm and learn.","8:45 - 9:30","Dilara Louise Hibbs"),
        Conference("4","Teacher Development","Mastering research competence & confidence: the chief editor’s view.","8:45 - 9:30","Tverdokchlebova Irina"),
        Conference("5","Digital Methodology","Project-based learning: digital tools worth adopting.","8:45 - 9:30","Aleshenko Olga, Ponidelko Lubov"),
        Conference("6","New Frontiers of Testing and Assessment","Less is more: using pictures to maximize student engagement and cut down class preparation time for Cambridge B2 First and C1 advanced.","9:35-10:20","Ribeiro Danilo"),
        Conference("7","Digital Methodology","An online simulated teaching intervention boosts preservice teacher self-efficacy and classroom readiness.","9:35-10:20","Klassen Robert"),
        Conference("8","Teacher Development","Pixton as a story-telling tool in ELT classroom.","9:35-10:20","Usoltseva Yulia, Wickrama Inosha"),
        Conference("9","Digital Methodology","Realia-based online lessons in the ELT.","9:35-10:20","Stepichev Petr"),
        Conference("10","Learner Diversity and Inclusion","Teaching simultaneous interpreting online: instruction and self-coaching.","9:35-10:20","Konurbaev Marklen"),
        Conference("11","Teacher Development","Language learner differences: seeing, testing, dealing.","9:35-10:20","Igolkina Natalia"),
        Conference("12","English Language Research","Intercultural awareness in the classroom.","13:30 - 14:15","Vasiliuskaite Giedre"),
        Conference("13","Digital Methodology","Incorporating digital social media research into qualitative research methodologies.","14:15 - 15:00","Christiansen Martha"),
        Conference("14","English Language Research","What does it mean to be polite?","15:00 - 15:45","Golechkova Tatiana"),
        Conference("15","Teacher Development","Rising to the digital challenge: introducing LanguageCert’s latest online assessment solutions.","16:00 - 16:45","Golding Janet"),
        Conference("16","Digital Methodology","Better online outcomes.","16:00 - 16:45","Dellar Hugh"),
    )
    for (i in 0..arrayConferenceWorkshop4.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Workshop").child(i.toString()).setValue(arrayConferenceWorkshop4[i])
    }
}

fun setConferencesSessions(){
    val arrayConferenceSession3 = arrayListOf<Conference>(
        Conference("1", "English Language Research", "Dictionary use in digital era (with special reference to English learners' dictionaries).", "13.30-13.45", "Karpova Olga"),
        Conference("2", "English Language Research", "Relevance of EMI for Higher Education in Russian universities.", "13.30-13.45", "Bulina Evgeniya, Burenina Natalya, Gvozdeva Marina, Pan'ko Varvara, Zvezdova Aleksandra"),
        Conference("3", "Intercultural Communication", "A country's tourism slogan as a marker of national identity.", "13.30-13.45", "Shilova Ekaterina"),
        Conference("4", "English Language Research", "Achromatic colours in the image of the city in Russian and English-language imaginative prose.", "13.45-14.00", "Barabushka Irina"),
        Conference("5", "English Language Research", "Content and language integrated learning for university students.", "13.45-14.00", "Melekhina Elena"),
        Conference("6", "Intercultural Communication", "An international project \"Daily meals in Russia and in the USA as they are viewed by Russian elementary schoolchildren\".", "13.45-14.00", "Kravchenko Natalya"),
        Conference("7", "English Language Research", "Contrastive analysis of the names of lovers in English and Russian languages.", "14.00-14.15", "Fedosova Valentina"),
        Conference("8", "English Language Research", "A look into learning environment at Touchstone@MISIS language programme: 2015-2020.", "14.00-14.15", "Ermakova Polina"),
        Conference("9", "Intercultural Communication", "TOPIC TBC", "14.00-14.15", "Kirpicheva Darya"),
        Conference("10", "English Language Research", "Challenges for ESP medical classroom.", "14.15-14.30", "Rodicheva Anna, Zaitseva Tatiana"),
        Conference("11", "English Language Research", "The translation competition of contemporary British prose in Perm region.", "14.15-14.30", "Gritsenko Elena, Polyakova Svetlana"),
        Conference("12", "Intercultural Communication", "TOPIC TBC", "14.15-14.30", "Rodko Olga"),
        Conference("13", "English Language Research", "Determination of the optimal translated correspondence.", "14.30-14.45", "Lukina Lyudmila"),
        Conference("14", "English Language Research", "Network community for the transport university students training for professional communication (a case study for exchange programme \"International Logistics\" (Ural State University for Railway Transport, Russia - Wildau Technical University, Germany).", "14.30-14.45", "Panchenkova Maria"),
        Conference("15", "English Language Research", "TOPIC TBC", "14.45-15.00", "Erken Emily"),
        Conference("16", "English Language Research", "To the problem of revealing national peculiarity of semantics.", "14.45-15.00", "Sternina Marina"),
        Conference("17", "Teacher Development", "English in a multilingual world: implications for teachers.", "15.15-15.30", "De Jong Ester"),
        Conference("18", "English Language Research", "Methodological issues in digital multimodal literacy research.", "15.15-15.30", "Yi Youngjoo"),
        Conference("19", "Digital Methodology", "Expanding meaning-making possibilities through multimodal composition.", "15.15-15.30", "Smith Blaine"),
        Conference("20", "Teacher Development", "Professional self-development of teachers in online environment.", "15.30-15.45", "Avdeeva Yulia, Grashchenkova Galina"),
        Conference("21", "English Language Research", "Information literacy for classroom research.", "15.30-15.45", "Safonkina Olga"),
        Conference("22", "Digital Methodology", "Blended Learning via Distance Learning: New Approaches in Teaching English.", "15.30-15.45", "Igonina Galina"),
        Conference("23", "Teacher Development", "Designing the course \"ICT in Education\" for beginning ESL teachers at Astrakhan State University.", "15.45-16.00", "Galichkina Elena"),
        Conference("24", "English Language Research", "CLIL-approach as extra motivation for students aged 10-12.", "15.45-16.00", "Yepova Olga"),
        Conference("25", "Digital Methodology", "Hybrid environment: managing a class.", "15.45-16.00", "Ponidelko Lyubov, Petrova Natalya"),
        Conference("26", "Teacher Development", "In-service training for ELT: project-based learning with authentic mass media.", "16.00-16.15", "Dvorghets Olga"),
        Conference("27", "English Language Research", "Mind mapping and its implementation into educational process.", "16.00-16.15", "Krivenko Lyudmila"),
        Conference("28", "Digital Methodology", "The peculiarities of teaching ESP online only.", "16.00-16.15", "Klochko Konstantin"),
        Conference("29", "Teacher Development", "Adapting teachers to changes in the curriculum.", "16.15-16.30", "Kiryukina Natalya"),
        Conference("30", "English Language Research", "Linguodidactic features of teaching English at the primary level.", "16.15-16.30", "Skachkova Sofia"),
        Conference("31", "Digital Methodology", "Establishing productive learning environment under any technical circumstances.", "16.15-16.30", "Kovaleva Yelena"),
        Conference("32", "English Language Research", "TOPIC TBC", "16.30-16.45", "Kolesnikova Marina"),
        Conference("33", "Digital Methodology", "Mobile learning and foreign language teaching. How do they align?", "16.30-16.45", "Golova Ekaterina")
    )

    initFirebase()
    for (i in 0..arrayConferenceSession3.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 3").child("Session").child(i.toString()).setValue(arrayConferenceSession3[i])
    }

    val arrayConferenceSession4 = arrayListOf<Conference>(
        Conference("1", "Digital Methodology", "Looking to the new normal: Qualitative methodologies for a post-pandemic era.", "13.30-13.45", "Christiansen M. Sidury"),
        Conference("2", "New Frontiers of Testing and Assessment", "National Discourse Pattern of Texts in Teaching FL.", "13.30-13.45", "Korovina Irina"),
        Conference("3", "Teacher Development", "From shaken and stirred to blended and flipped – the future of ELT?", "13.30-13.45", "Evans Edward"),
        Conference("4", "Digital Methodology", "Developing digital literacy in the process of language learning.", "13.45-14.00", "Kravets Olga"),
        Conference("5", "New Frontiers of Testing and Assessment", "Internet-based exam: advantages, disadvantages, possible prospects for use.", "13.45-14.00", "Kharlamenko Inna"),
        Conference("6", "Teacher Development", "Teaching gifted and talented learners.", "13.45-14.00", "Novikova Vera"),
        Conference("7", "Digital Methodology", "Universal collaborative ESP/ESL teaching methods in digital education: first approach.", "14.00-14.15", "Vasilyeva Irina"),
        Conference("8", "New Frontiers of Testing and Assessment", "Assessment as a digital learning tool.", "14.00-14.15", "Vladyko Anzhelika"),
        Conference("9", "Teacher Development", "Student-friendly approach to language classes for engineering students: example of NUST MISiS English programme.", "14.00-14.15", "Knyazeva Olga, Polukhina Irina"),
        Conference("10", "Digital Methodology", "Creating Student-Centred Project-Based Courses in English using Moodle.", "14.15-14.30", "Kuznetsova Elena"),
        Conference("11", "New Frontiers of Testing and Assessment", "Factors contributing to the final IELTS exam in the volatile university environment.", "14.15-14.30", "Rossikhina Olga, Chernushkina Nina"),
        Conference("12", "Teacher Development", "How to plan and organize your online class.", "14.15-14.30", "Vostrikova Irina"),
        Conference("13", "Digital Methodology", "Language teaching in a (post) pandemic world.", "14.30-14.45", "Mogunova Elena"),
        Conference("14", "New Frontiers of Testing and Assessment", "Language assessment essentials for future teachers.", "14.30-14.45", "Gorizontova Anna"),
        Conference("15", "Teacher Development", "Touchstone@MISIS Culture and Programme manual.", "14.30-14.45", "Isaeva Anna, Esipov Roman"),
        Conference("16", "Digital Methodology", "Methodological potential of using integrated multimedia tasks in teaching professional English to non-language students.", "14.45-15.00", "Lavrentyeva Natalya"),
        Conference("17", "Digital Methodology", "Narrative modeling with StoryQ: integrating language arts, mathematics, and computing to create pathways to artificial intelligence careers.", "15.15-15.30", "Jiang Shiyan"),
        Conference("18", "Learner Diversity and Inclusion", "Longread as an efficient tool for developing learner autonomy through creative mindset in digital space.", "15.15-15.30", "Belkina Oksana, Lazorak Olga, Yaroslavova Elena"),
        Conference("19", "Teacher Development", "Language, perception, and TEFL: implications for teacher development.", "15.15-15.30", "Kravchenko Aleksander"),
        Conference("20", "Digital Methodology", "Teaching foreign languages online: a mixed blessing?", "15.30-15.45", "Yankova Diana"),
        Conference("21", "Learner Diversity and Inclusion", "TOPIC TBC", "15.30-15.45", "Gorelova Natalie"),
        Conference("22", "Teacher Development", "Learning L2 grammar from original version TV series.", "15.30-15.45", "Pattemore-Plotnikova Anastasia"),
        Conference("23", "Digital Methodology", "Digital tools for Academic Writing Course design.", "15.45-16.00", "Tikhomirova Elizaveta"),
        Conference("24", "Learner Diversity and Inclusion", "Assistive technology in teaching EFL to children with hearing impairments.", "15.45-16.00", "Cheprasova Tatyana"),
        Conference("25", "Teacher Development", "Key vectors of educational interactivity implementation in English classroom.", "15.45-16.00", "Bogatyrev Andrei"),
        Conference("26", "Digital Methodology", "Integration of the digital storytelling into the ELT classroom.", "16.00-16.15", "Arkhipova Tatiana"),
        Conference("27", "Digital Methodology", "Adapting Dictogloss to digital transformation.", "16.15-16.30", "Teplyakova Anastasia"),
        Conference("28", "Digital Methodology", "A Case Study: Interdisciplinary Project Work in ELT Using Python At NUST \"MISiS\"", "16.30-16.45", "Komarova Maria"),
    )

    for (i in 0..arrayConferenceSession4.size-1){
        REF_DATABASE_ROOT.child(NODE_CONFERENCES).child("june 4").child("Session").child(i.toString()).setValue(arrayConferenceSession4[i])
    }
}