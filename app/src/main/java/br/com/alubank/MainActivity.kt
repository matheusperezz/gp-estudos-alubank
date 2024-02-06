package br.com.alubank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alubank.ui.theme.AlubankTheme
import br.com.alubank.ui.theme.fontColor
import br.com.alubank.ui.theme.projectBackground
import br.com.alubank.ui.theme.projectPrimary
import br.com.alubank.ui.theme.projectSurface
import br.com.alubank.ui.theme.textStyleCommonLarge
import br.com.alubank.ui.theme.textStyleCommonMedium
import br.com.alubank.ui.theme.textStyleCommonSmall

/*
*   Model dirigindo o layout
* */
data class AccountAction(
    val text: String,
    val icon: Painter,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val accountSamples = listOf(
                AccountAction(
                    text = "Depositar",
                    icon = painterResource(id = R.drawable.ic_wallet)
                ),
                AccountAction(
                    text = "Transferir",
                    icon = painterResource(id = R.drawable.ic_cached)
                ),
                AccountAction(
                    text = "Ler",
                    icon = painterResource(id = R.drawable.ic_center_focus)
                )
            )

            AlubankTheme {
                Surface(
                    color = projectBackground,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        AccountHeader()
                        AccountActivity()
                        AccountActionsSection(accountSamples)
                    }
                }
            }
        }
    }
}

@Composable
fun AccountHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
            .background(projectPrimary)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "$ 1000.00",
                style = TextStyle(
                    color = fontColor,
                    fontSize = 26.sp
                ),
            )
            Text(
                text = "Balanço disponível",
                style = TextStyle(
                    color = fontColor,
                    fontSize = 14.sp
                ),
            )
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = null,
                tint = fontColor,
                modifier = Modifier
                    .size(45.dp)
                    .clickable { }
            )
        }
    }
}

@Composable
fun AccountActivity() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {

            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = projectSurface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Total gasto", style = textStyleCommonSmall)
            Text(text = "$9900.97", style = textStyleCommonLarge)
            Text(
                text = "Esse mês você gastou $1500.00 com jogos. Tente abaixar esse custo!",
                style = textStyleCommonSmall
            )
            TextButton(onClick = { }) {
                Text(
                    text = "Diga-me como",
                    style = TextStyle(color = projectPrimary, fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
fun AccountActionsSection(
    accountList: List<AccountAction>
) {
    Column {
        Text(
            text = "Ações da conta",
            style = TextStyle(color = fontColor, fontSize = 24.sp),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(accountList) { acc ->
                ActionCard(text = acc.text, icon = acc.icon)
            }
        }
    }

}

@Composable
fun ActionCard(
    text: String,
    icon: Painter,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(110.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = projectSurface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth()
        ) {
            Icon(painter = icon, tint = fontColor, contentDescription = null)
            Text(
                text = text, style = TextStyle(
                    color = fontColor
                )
            )
        }
    }
}