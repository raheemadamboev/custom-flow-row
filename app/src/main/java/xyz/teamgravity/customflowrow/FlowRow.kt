package xyz.teamgravity.customflowrow

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val fuckedConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurables.map { it.measure(fuckedConstraints) }
            val groupedPlaceables = mutableListOf<List<Placeable>>()
            var currentGroupedPlaceables = mutableListOf<Placeable>()
            var currentGroupedPlaceablesWidth = 0

            placeables.forEach { placeable ->
                if (currentGroupedPlaceablesWidth + placeable.width > constraints.maxWidth) {
                    groupedPlaceables.add(currentGroupedPlaceables)
                    currentGroupedPlaceables = mutableListOf(placeable)
                    currentGroupedPlaceablesWidth = placeable.width
                } else {
                    currentGroupedPlaceables.add(placeable)
                    currentGroupedPlaceablesWidth += placeable.width
                }
            }

            if (currentGroupedPlaceables.isNotEmpty()) {
                groupedPlaceables.add(currentGroupedPlaceables)
            }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight
            ) {
                var yPosition = 0
                groupedPlaceables.forEach { row ->
                    var xPosition = 0
                    row.forEach { placeable ->
                        placeable.place(
                            x = xPosition,
                            y = yPosition
                        )
                        xPosition += placeable.width
                    }
                    yPosition += row.maxOfOrNull { it.height } ?: 0
                }
            }
        },
        content = content,
    )
}