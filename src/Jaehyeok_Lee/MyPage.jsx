import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";
import Button from "./Button";
import data from "../data.json"

const Wrapper = styled.div`
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const Container = styled.div`
    width: 100%;
    max-width: 300px;
    & > * {
        :not(:last-child) {
            margin-bottom: 30px;
        }
    }
`;

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`;

const Label = styled.p`
    font-size: 16px;
    font-weight: 500;
`;

function MyPage(props) {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const { postId } = useParams();

    const handleChangeName = (event) => {
        setName(event.target.value);
    };

    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };

    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        alert(`수정이 완료되었습니다.`);
        // 변경 데이터 저장하기
        event.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit}>
            <Wrapper>
                <Container>
                    <PostContainer>
                        <Label>
                            이　　름　
                            <input type="text" value={name} onChange={handleChangeName} />
                        </Label>
                        <Label>
                            이 메 일　
                            <input type="text" value={email} onChange={handleChangeEmail} />
                        </Label>
                        <Label>
                            비밀번호　
                            <input type="password" value={password} onChange={handleChangePassword} />
                        </Label>
                    </PostContainer>
                    <Button
                        type="submit"
                        title="정보 수정"
                        onClick={() => {navigate("/");}}
                    />
                </Container>
            </Wrapper>
        </form>
    );
}

export default MyPage;