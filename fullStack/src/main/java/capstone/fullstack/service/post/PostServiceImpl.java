package capstone.fullstack.service.post;

import capstone.fullstack.domain.Post;
import capstone.fullstack.domain.User;
import capstone.fullstack.dto.post.*;
import capstone.fullstack.exception.PostException;
import capstone.fullstack.exception.PostExceptionType;
import capstone.fullstack.repository.post.PostRepository;
import capstone.fullstack.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final FileService fileService;


    @Override
    public void save( User user, PostSaveDto postSaveDto) {
        Post post = postSaveDto.toEntity();

        post.confirmWriter(user);

        postSaveDto.uploadFile().ifPresent(
                file->post.updateFilePath(fileService.save(file))
        );

    }

    @Override
    public void update(User user, Long id, PostUpdateDto postUpdateDto) {

        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(PostExceptionType.POST_NOT_FOUND));

        checkAuthority(user, post, PostExceptionType.NOT_AUTHORITY_UPDATE_POST);

        postUpdateDto.title().ifPresent(post::updateTitle);
        postUpdateDto.content().ifPresent((post::updateContent));

        if(post.getFilePath() != null){
            fileService.delete(post.getFilePath());
        }
        postUpdateDto.uploadFile().ifPresentOrElse(
                multipartFile -> post.updateFilePath(fileService.save(multipartFile)),
                () -> post.updateFilePath(null)
        );
    }

    @Override
    public void delete(User user, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(PostExceptionType.POST_NOT_FOUND));

        checkAuthority(user, post, PostExceptionType.NOT_AUTHORITY_DELETE_POST);
    }

    @Override
    public PostInfoDto getPostInfo(Long id) {

        return new PostInfoDto(postRepository.findWithWriterById(id)
                .orElseThrow(()->new PostException(PostExceptionType.POST_NOT_FOUND)));
    }

    @Override
    public PostPagingDto getPostList(Pageable pageable, PostSearchCondition postSearchCondition) {

        return new PostPagingDto(postRepository.search(postSearchCondition, pageable));
    }

    private void checkAuthority(User user, Post post, PostExceptionType postExceptionType){
        if(!post.getWriter().getUserId().equals(user.getUserId()))
            throw new PostException(postExceptionType);
    }
}
