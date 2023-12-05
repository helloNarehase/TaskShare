package com.nare.taskshare

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nare.taskshare.ui.theme.Accessible_Dark
import com.nare.taskshare.ui.theme.Accessible_Light
import com.nare.taskshare.ui.theme.AppleDarkAccessible
import com.nare.taskshare.ui.theme.AppleDarkAccessible2
import com.nare.taskshare.ui.theme.Default_Light2
import com.nare.taskshare.ui.theme.Default_Light3
import com.nare.taskshare.ui.theme.Orange
import com.nare.taskshare.ui.theme.Puple
import com.nare.taskshare.ui.theme.TaskShareTheme
import com.nare.taskshare.ui.theme.The_Light1
import com.nare.taskshare.ui.theme.The_Light2
import com.nare.taskshare.ui.theme.The_Light_Dark
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

val titles = "TaskShare"
val subtitle = "Task"

data class ToDos(
    val taskName:String = "Task",
    val taskContent: String = "is Task Content",
    val startDay: Long = System.currentTimeMillis(),
    val finishiDay: Long = System.currentTimeMillis(),
    val taskImportance: Int = 3
)

class MainActivity : ComponentActivity(), ModelHelper.Luna {

    lateinit var gen:ModelHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gen = ModelHelper(
            this, this.assets
        )
        val todos = ToDos()
//        val dbHelper = FeedReaderDbHelper(this)
//        val db = dbHelper.writableDatabase
//
//        val values = ContentValues().apply {
//            this.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, titles)
//            this.put(FeedReaderContract.FeedEntry.COLUMN_TEXT_SUBTITLE, subtitle)
//        }

//        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)

        when(intent.action)
        {
            Intent.ACTION_SEND -> {

                if(intent.type == "text/*")
                {
//                    intent.dataString
                    Toast.makeText(this, "${intent.type}, ${intent.getStringExtra(Intent.EXTRA_TEXT)}", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this, "text Send", Toast.LENGTH_SHORT).show()
                }


//                if(intent.type == "image/*")
//                {
//                    Toast.makeText(this, "${intent.type}, ${intent.getParcelableExtra}", Toast.LENGTH_SHORT).show()
////                    Toast.makeText(this, "img Send", Toast.LENGTH_SHORT).show()
//                }
                finish()
            }
        }

        setContent {
            TaskShareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = The_Light_Dark
                ) {
//                    LazyColumn(content = {
//                        for(i:Int in 1..1) {
//                            item {
//                                Row (
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(top = 5.dp, bottom = 5.dp),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.SpaceEvenly,
//                                ){
//                                    Task_Card("Todo One",
//                                        modifier = Modifier
////                                .padding(top = 10.dp, bottom = 10.dp)
//                                            .clip(RoundedCornerShape(15.dp))
//                                            .background(The_Light1)
//                                    )
////                        Text(text = "Edit", textAlign = TextAlign.Center)
//                                    Icon(Icons.Rounded.FavoriteBorder, contentDescription = "IconBookMaker")
//                                }
//                            }
//                        }
//                    })

                    val text = remember{
                        mutableStateOf("hello world")
                    }

                    val Op_TF = remember{
                        mutableStateOf(true)
                    }
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                            CuustomTextF(
                                modifier = Modifier
//                    .background(MaterialTheme.colorScheme.background)
                                    .clip(RoundedCornerShape(20.dp))
                                    .size(300.dp, 100.dp)
                                    .border(width = 2.dp, color = Orange, shape = RoundedCornerShape(20.dp))

                                ,
                                str = text.value,
                                edit = {text.value = it},
                                tf = Op_TF.value
                            )
                        }
                        Button(onClick = {
                            Op_TF.value = false
                            gen.gen(text.value)
                        }) {

                        }
                    }
                }
            }
        }
    }

    override fun onResult(results: String) {

    }

}
@Composable
fun ProgressBar_Linear(modifier:Modifier, progress:Float = 1f)
{
    var proress = remember {
        Animatable(0f)
    }
    LaunchedEffect(proress) {
        proress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = 800, easing = EaseOut),
        )
    }
    Log.e("fafafa", proress.toString())

    Canvas(modifier = modifier, onDraw = {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            color = Accessible_Light,
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = 60.dp.value,
            cap = StrokeCap.Round
        )

        drawLine(
            color = AppleDarkAccessible2,
            start = Offset(x = proress.value*canvasWidth, y = 0f),
            end = Offset(x = 0f, y = 0f),
            strokeWidth = 60.dp.value,
            cap = StrokeCap.Round
        )
    })
}

@SuppressLint("SimpleDateFormat")
@Composable
fun Task_Card(title:String = "ToDo Title",
              startDay: Long = System.currentTimeMillis(),
              endDay: Long = System.currentTimeMillis(),
              modifier:Modifier = Modifier,
              contentForward : () -> Unit = {},
              edit : () -> Unit = {},

){

    var endDays by rememberSaveable {
        mutableStateOf(startDay)
    }

    var startDays by rememberSaveable {
        mutableStateOf(endDay)
    }
    var hd = LocalHapticFeedback.current
    var cn = LocalContext.current

    var dialog_enble by rememberSaveable {
        mutableStateOf(false)
    }

    var dialog_enbleEnd by rememberSaveable {
        mutableStateOf(false)
    }

    var dialog_enbleStart by rememberSaveable {
        mutableStateOf(false)
    }
    val dataFormat = SimpleDateFormat("yy-MM-dd")

    val a by rememberSaveable {
        mutableStateOf(title)
    }
    if(dialog_enble)
    {
        Dialog(onDismissRequest = {
            dialog_enbleEnd = false
            dialog_enbleStart = false
            dialog_enble = false
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                var y by rememberSaveable {
                    mutableStateOf(2023)
                }
                var m by rememberSaveable {
                    mutableStateOf(1)
                }
                var d by rememberSaveable {
                    mutableStateOf(1)
                }
                var offsetY by remember { mutableStateOf(0f) }
                var offsetM by remember { mutableStateOf(0f) }
                var offsetD by remember { mutableStateOf(0f) }
                val ap = if(m <= 7 && m % 2 == 0 && m != 2) 30 else
                    if (m <= 7 && m % 2 != 0) 31 else
                    if (m > 7 && m % 2 == 0) 31 else
                    if (m > 7) 30 else
                    if (y % 4 == 0 || y % 100 == 0) 29 else 28

                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(text = "set Time", fontSize = 24.sp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = String.format("%02d", y), fontSize = 30.sp, modifier = Modifier
                            .scrollable(
                                orientation = Orientation.Vertical,
                                // Scrollable state: describes how to consume
                                // scrolling delta and update offset
                                state = rememberScrollableState { delta ->
                                    offsetY += delta
                                    Log.e("offsetY", "$offsetY")
                                    if(offsetY > 60) {
                                        offsetY = 0f
                                        y += 1
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    } else if(offsetY < -60) {
                                        offsetY = 0f
                                        y -= 1
                                        if(y < 0) y = 1999
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    }
                                    if(d > ap){
                                        d = ap
                                    }
                                    delta
                                }
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = String.format("%02d", m), fontSize = 30.sp, modifier = Modifier
                            .scrollable(
                                orientation = Orientation.Vertical,
                                // Scrollable state: describes how to consume
                                // scrolling delta and update offset
                                state = rememberScrollableState { delta ->
                                    offsetM += delta
                                    if(offsetM > 60) {
                                        offsetM = 0f
                                        m += 1
                                        if(m > 12) m = 1
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    } else if(offsetM < -60) {
                                        offsetM = 0f
                                        m -= 1
                                        if(m < 1) m = 12
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    }
                                    if(d > ap){
                                        d = ap
                                    }
                                    delta
                                }
                            ))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = String.format("%02d", d), fontSize = 30.sp, modifier = Modifier
                            .scrollable(
                                orientation = Orientation.Vertical,
                                // Scrollable state: describes how to consume
                                // scrolling delta and update offset
                                state = rememberScrollableState { delta ->
                                    offsetD += delta
                                    if(offsetD > 60) {
                                        offsetD = 0f
                                        d += 1
                                        if(d > ap){
                                            d = 1
                                        }
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    } else if(offsetD < -60) {
                                        offsetD = 0f
                                        d -= 1
                                        if(d < 1){
                                            d = ap
                                        }
                                        hd.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    }
                                    delta
                                }
                            )
                        )
                    }
                    Row (horizontalArrangement = Arrangement.End)
                    {
                        val cal = Calendar.getInstance()
                        cal.timeZone = TimeZone.getTimeZone("UTC0")
                        cal.set(y, m-1, d)

                        Icon(Icons.Rounded.ArrowForward, contentDescription = "C", Modifier.clickable {
                            if(dialog_enbleEnd) {
                                dialog_enbleEnd = false
                                if(cal.timeInMillis > startDays)
                                {
                                    endDays = cal.timeInMillis
                                    dialog_enbleEnd = false
                                    dialog_enble = false
                                } else {
                                    Toast.makeText(cn, "마감일은 시작일 뒤에 설정을 해야합니다.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            if(dialog_enbleStart) {
                                dialog_enbleStart = false
                                if(cal.timeInMillis < endDays)
                                {
                                    startDays = cal.timeInMillis
                                    dialog_enbleStart = false
                                    dialog_enble = false
                                } else {
                                    Toast.makeText(cn, "시작일은 마감일 앞에 설정을 해야합니다.", Toast.LENGTH_SHORT).show()
                                }
                            }

                        })
                    }
                }

            }
        }
    }

    Box (modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .size(300.dp, 100.dp),
        contentAlignment = Alignment.Center
    ){
        Column {
            Row(
                modifier = Modifier.width(240.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier
                        .clip(CircleShape)
                        .background(The_Light2)
                        .padding(start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = a, fontSize = 24.sp, modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp))
                    Icon(Icons.Default.Create, contentDescription = "create", modifier = Modifier.size(20.dp))
                }
                Icon(Icons.Default.ArrowForward, contentDescription = "forward", modifier = Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                ProgressBar_Linear(modifier = Modifier
                    .width(230.dp)
                    .height(0.dp), .89f)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.width(240.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = dataFormat.format(startDays), fontSize = 12.sp, modifier = Modifier.clickable {
                        dialog_enbleStart =! dialog_enbleStart
                        dialog_enble =! dialog_enble
                    })
                    Text(text = dataFormat.format(endDays), fontSize = 12.sp, modifier = Modifier.clickable {
                        dialog_enbleEnd =! dialog_enbleEnd
                        dialog_enble =! dialog_enble
                    })
                }
            }

        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun CuustomTextF(
    modifier: Modifier,
    str:String,
    edit:(String) -> Unit,
    tf:Boolean
    )
{

    val focusRequester = remember {
        FocusRequester()
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val focusManager = LocalFocusManager.current
    val wide = rememberSaveable { mutableStateOf(64f) }
    val animatedValue = remember { Animatable(64f) }
    LaunchedEffect(isFocused) {
        if(isFocused) {
            animatedValue.animateTo(0f, animationSpec= tween(durationMillis = 800))
        }
        else {
            focusManager.clearFocus()
            animatedValue.animateTo(1f, animationSpec= tween(durationMillis = 800))
        }
    }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Orange,
        backgroundColor = Orange
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            value = str,
            onValueChange = {edit(it)},
            modifier = modifier
                .size(300.dp, (100.dp.value + 200.dp.value*wide.value).dp),
            cursorBrush = SolidColor(Puple),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            enabled = tf,
            decorationBox = {
                Row (
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxSize()
                        .padding(15.dp)
                ){
                    it()
                }
            }

        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(
        color = The_Light_Dark
    ) {
//        LazyColumn(content = {
//            for(i:Int in 1..1) {
//                item {
//                    Row (
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 5.dp, bottom = 5.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceEvenly,
//                    ){
//                        Task_Card("Todo One",
//                            modifier = Modifier
////                                .padding(top = 10.dp, bottom = 10.dp)
//                                .clip(RoundedCornerShape(15.dp))
//                                .background(The_Light1)
//                        )
////                        Text(text = "Edit", textAlign = TextAlign.Center)
//                        Icon(Icons.Rounded.FavoriteBorder, contentDescription = "IconBookMaker")
//                    }
//                }
//            }
//        })
        val text = remember{
            mutableStateOf("hello world")
        }

        val Op_TF = remember{
            mutableStateOf(true)
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                CuustomTextF(
                    modifier = Modifier
//                    .background(MaterialTheme.colorScheme.background)
                        .clip(RoundedCornerShape(20.dp))
                        .size(300.dp, 100.dp)
                        .border(width = 2.dp, color = Orange, shape = RoundedCornerShape(20.dp))

                    ,
                    str = text.value,
                    edit = {text.value = it},
                    tf = Op_TF.value
                )
            }
            Button(onClick = {}) {
                
            }
        }
    }
}