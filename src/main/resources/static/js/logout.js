document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("logoutButton").addEventListener("click", function() {
        fetch("/logout", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    console.error("로그아웃 실패");
                }
            })
            .catch(error => {
                console.error("로그아웃 요청 실패:", error);
            });
    });
});
