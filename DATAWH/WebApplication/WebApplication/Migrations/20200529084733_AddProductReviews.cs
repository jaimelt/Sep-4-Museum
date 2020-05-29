using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication.Migrations
{
    public partial class AddProductReviews : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Rooms_RoomMeasurements_LiveRoomMeasurementsId",
                table: "Rooms");

            migrationBuilder.DropIndex(
                name: "IX_Rooms_LiveRoomMeasurementsId",
                table: "Rooms");

            migrationBuilder.DropColumn(
                name: "FirstName",
                table: "Visitors");

            migrationBuilder.DropColumn(
                name: "LiveRoomMeasurementsId",
                table: "Rooms");

            migrationBuilder.DropColumn(
                name: "Email",
                table: "Administrators");

            migrationBuilder.AddColumn<string>(
                name: "FirstName",
                table: "Visitors",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "roomNo",
                table: "RoomMeasurements",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Email",
                table: "Administrators",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "FirstName",
                table: "Visitors");

            migrationBuilder.DropColumn(
                name: "roomNo",
                table: "RoomMeasurements");

            migrationBuilder.DropColumn(
                name: "Email",
                table: "Administrators");

            migrationBuilder.AddColumn<string>(
                name: "FirstName",
                table: "Visitors",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "LiveRoomMeasurementsId",
                table: "Rooms",
                type: "int",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Email",
                table: "Administrators",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.CreateIndex(
                name: "IX_Rooms_LiveRoomMeasurementsId",
                table: "Rooms",
                column: "LiveRoomMeasurementsId");

            migrationBuilder.AddForeignKey(
                name: "FK_Rooms_RoomMeasurements_LiveRoomMeasurementsId",
                table: "Rooms",
                column: "LiveRoomMeasurementsId",
                principalTable: "RoomMeasurements",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
