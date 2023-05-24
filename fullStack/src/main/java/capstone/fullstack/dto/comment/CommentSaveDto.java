package capstone.fullstack.dto.comment;

import capstone.fullstack.domain.Comment;

public record CommentSaveDto(String content) {

    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }
}
