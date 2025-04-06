package web.peasantgaming.dto.cheapshark;

import java.awt.print.Pageable;
import java.util.List;

public class ParentGameIdDto {

    private List<GameIdDto> gameIdDtoList;

    public ParentGameIdDto(List<GameIdDto> gameIdDtoList) {
        this.gameIdDtoList = gameIdDtoList;
    }
    public ParentGameIdDto() {}

    public List<GameIdDto> getGameIdDtoList() {
        return gameIdDtoList;
    }

    public void setGameIdDtoList(List<GameIdDto> gameIdDtoList) {
        this.gameIdDtoList = gameIdDtoList;
    }
}
